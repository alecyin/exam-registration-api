package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.*;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import com.exam.registration.util.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author yhf
 * @classname OrderController
 **/
@RequestMapping("/orders")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private MajorService majorService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertOrder(@RequestBody Order order) {
        if (StringUtils.isEmpty(order.getExamId())) {
            return MsgUtils.fail("考试id不能为空");
        }
        if (StringUtils.isEmpty(order.getCost())) {
            return MsgUtils.fail("金额不能为空");
        }
        if (StringUtils.isEmpty(order.getExamineeNumber())) {
            return MsgUtils.fail("准考证号不能为空");
        }
        if (StringUtils.isEmpty(order.getStudentId())) {
            return MsgUtils.fail("学生id不能为空");
        }

        if (Objects.isNull(studentService.getStudentByPrimaryKey(order.getStudentId()))) {
            return MsgUtils.fail("学生不存在");
        }
        if (Objects.isNull(examService.getExamByPrimaryKey(order.getExamId()))) {
            return MsgUtils.fail("考试不存在");
        }

        int res = orderService.insertOrder(order);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOrder(@PathVariable("id") long id) {
        int res = orderService.deleteOrderByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllOrder(@RequestParam("ids") String ids) {
        int res = orderService.deleteOrderByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteOrder(@PathVariable("id") long id) {
        Order order = new Order();
        order.setId(id);
        order.setIsDeleted(true);
        int res = orderService.updateOrderByPrimaryKeySelective(order);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateOrder(@RequestBody Order order) {
        int res = orderService.updateOrderByPrimaryKeySelective(order);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    public String listOrders() {
        List<Order> list = orderService.listOrders();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listExamsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Order> list = orderService.listOrdersByPage(map);
        long pageTotal = orderService.countOrders(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("pageTotal", pageTotal);
        JSONArray jsonArray = new JSONArray();
        for (Order order : list) {
            StringBuilder jsonExam = new StringBuilder(JSONObject.toJSONString(order));
            long qExamId = order.getExamId();
            long qStudentId = order.getStudentId();
            Student student = studentService.getStudentByPrimaryKey(qStudentId);
            String examName = examService.getExamByPrimaryKey(qExamId).getName();
            Map<String, Object> subMap = new HashMap<>();
            subMap.put("studentName", student.getName());
            subMap.put("idCardNumber", student.getIdCardNumber());
            subMap.put("examName", examName);
            String jsonSubMap = JSONObject.toJSONString(subMap);
            StringBuilder sb = new StringBuilder(jsonSubMap).deleteCharAt(0);
            jsonExam.deleteCharAt(jsonExam.length() - 1).append(",").append(sb);
            jsonArray.add(JSONObject.parse(jsonExam.toString()));
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getOrderByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(orderService.getOrderByPrimaryKey(id));
    }

    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    @ResponseBody
    public String apply(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        if (StringUtils.isEmpty(map.get("majorId"))) {
            return MsgUtils.fail("专业id不能为空");
        }
        if (StringUtils.isEmpty(map.get("siteId"))) {
            return MsgUtils.fail("考场id不能为空");
        }

        Long majorId = Long.valueOf(String.valueOf(map.get("majorId")));
        Long siteId = Long.valueOf(String.valueOf(map.get("siteId")));
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        Site site = siteService.getSiteByPrimaryKey(siteId);
        if (site.getAllowProvince().replace("，", "|")
                .indexOf(student.getAddress().split("|")[0]) == -1) {
            return MsgUtils.fail("生源地不允许报名该场考试");
        }

        Exam exam = examService.getExamByMajorIdAndSiteId(majorId, siteId);
        if (exam.getEndTime().before(new Date())) {
            return MsgUtils.fail("报名日期已截止");
        }
        if (orderService.countOrdersByExamId(exam.getId()) > 0) {
            return MsgUtils.fail("不允许重复报名");
        }
        Order order = new Order();
        order.setIsPaid(false);
        order.setExamId(exam.getId());
        order.setStudentId(student.getId());
        order.setCost(majorService.getMajorByPrimaryKey(majorId).getFee());
        int res = orderService.insertOrder(order);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/unpaid/apply-info",method = RequestMethod.GET)
    @ResponseBody
    public String getOrderByStudentId(HttpServletRequest request) {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        List<Order> list = orderService.listUnPaidOrdersByStudentId(student.getId());
        // 返回考生已报名的考点名称、专业名称、应缴金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        JSONArray jsonArray = new JSONArray();
        for (Order order : list) {
            Exam exam = examService.getExamByPrimaryKey(order.getExamId());
            Long majorId = exam.getMajorId();
            Long siteId = exam.getSiteId();
            Major major = majorService.getMajorByPrimaryKey(majorId);
            Site site = siteService.getSiteByPrimaryKey(siteId);
            String majorName = major.getName();
            BigDecimal fee = major.getFee();
            String siteName = site.getName();
            String address = site.getAddress();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("majorName", majorName);
            jsonObject1.put("siteName", siteName);
            jsonObject1.put("address", address);
            jsonObject1.put("fee", fee);
            jsonObject1.put("orderId", order.getId());
            jsonObject1.put("majorId", majorId);
            jsonObject1.put("siteId", siteId);
            jsonObject1.put("isPaid", order.getIsPaid());
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }

    @RequestMapping(path = "/all/apply-info",method = RequestMethod.GET)
    @ResponseBody
    public String getPaidOrderByStudentId(HttpServletRequest request) {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        List<Order> list = orderService.listOrdersByStudentId(student.getId());
        // 返回考生已报名的考点名称、专业名称、应缴金额、考号
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        JSONArray jsonArray = new JSONArray();
        for (Order order : list) {
            Exam exam = examService.getExamByPrimaryKey(order.getExamId());
            Long majorId = exam.getMajorId();
            Long siteId = exam.getSiteId();
            Major major = majorService.getMajorByPrimaryKey(majorId);
            Site site = siteService.getSiteByPrimaryKey(siteId);
            String majorName = major.getName();
            BigDecimal fee = major.getFee();
            String siteName = site.getName();
            String address = site.getAddress();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("majorName", majorName);
            jsonObject1.put("siteName", siteName);
            jsonObject1.put("startTime", exam.getStartTime());
            jsonObject1.put("endTime", exam.getEndTime());
            jsonObject1.put("isPaid", order.getIsPaid());
            if (order.getIsPaid()) {// 已支付的订单添加准考证号码
                jsonObject1.put("examineeNumber", order.getExamineeNumber());
            } else {
                jsonObject1.put("examineeNumber", "-");
            }
            jsonObject1.put("address", address);
            jsonObject1.put("fee", fee);
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }

    @RequestMapping(path = "/cancel-order", method = RequestMethod.POST)
    @ResponseBody
    public String cancelOrder(@RequestBody Map<String, Object> map,
                              HttpServletRequest request) {
        Long studentId = Long.valueOf((String) request.getAttribute("studentId"));
        Long orderId = Long.valueOf(String.valueOf(map.get("orderId")));
        Order order = orderService.getOrderByPrimaryKey(orderId);
        if (studentId.compareTo(order.getStudentId()) != 0) {
            return MsgUtils.fail("访问错误！");
        }
        if (order.getIsPaid()) {
            return MsgUtils.fail("缴费单已完成，无法删除！");
        }
        return orderService.deleteOrderByPrimaryKey(orderId) == 1 ?
                MsgUtils.success() : MsgUtils.fail("删除失败，刷新重试！");
    }

}
