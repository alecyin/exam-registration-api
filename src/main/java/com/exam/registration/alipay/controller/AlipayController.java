package com.exam.registration.alipay.controller;

import com.alibaba.fastjson.JSONObject;
import com.exam.registration.alipay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yhf
 * @classname AlipayController
 * @description TODO
 * @date 2020/2/17
 **/
@RestController
public class AlipayController {

    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;

    /**
     * web 订单支付
     */
    @GetMapping("getPagePay")
    public String getPagePay() throws Exception{
        /** 模仿数据库，从后台调数据*/
        String outTradeNo = UUID.randomUUID().toString();
        Integer totalAmount = 1000;
        String subject = "苹果28";
        String pay = alipayService.webPagePay(outTradeNo, totalAmount, subject);
        System.out.println(pay);
//        Map<Object, Object> pays = new HashMap<>();
//        pays.put("pay", pay);
        return pay;
    }

}


