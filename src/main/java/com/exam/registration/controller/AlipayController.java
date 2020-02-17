package com.exam.registration.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.exam.registration.config.AlipayConfig;
import com.exam.registration.model.*;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author yhf
 * @classname AlipayController
 * @description TODO
 * @date 2020/2/17
 **/
@RequestMapping("/alipay")
@RestController
public class AlipayController {

    @Autowired
    private AlipayService alipayService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private OrderService orderService;

    /**
     * web 订单支付
     */
    @RequestMapping(path = "/getWebPay", method = RequestMethod.POST)
    public String getPagePay(@RequestBody Map<String, Object> map,
                             HttpServletRequest request) throws Exception{
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        Long majorId = Long.valueOf(String.valueOf(map.get("majorId")));
        Long siteId = Long.valueOf(String.valueOf(map.get("siteId")));
        Long orderId = Long.valueOf(String.valueOf(map.get("orderId")));
        Major major = majorService.getMajorByPrimaryKey(majorId);
        Site site = siteService.getSiteByPrimaryKey(siteId);
        BigDecimal fee = major.getFee();
        //生成订单
        String outTradeNo = site.getCode()
                + major.getCode() + student.getId() + "-"
                + UUID.randomUUID().toString().replace("-","");
        String subject = site.getName() + "-" + major.getName();
        Order order = orderService.getOrderByPrimaryKey(orderId);
        order.setOrderNumber(outTradeNo);
        order.setCost(fee);
        orderService.updateOrderByPrimaryKeySelective(order);
        String pay = alipayService.webPagePay(outTradeNo, fee, subject);
//        Map<Object, Object> pays = new HashMap<>();
//        pays.put("pay", pay);
        return MsgUtils.success(pay);
    }

    @RequestMapping(path = "/notify", method = RequestMethod.POST)
    public void notify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
        } catch (com.alipay.api.AlipayApiException e) {
        }
        if (signVerified) {
            //处理你的业务逻辑，更细订单状态等
            System.out.println(out_trade_no);
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }

}


