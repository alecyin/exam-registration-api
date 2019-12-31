package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Exam;
import com.exam.registration.model.Order;
import com.exam.registration.model.Student;
import com.exam.registration.service.ExamService;
import com.exam.registration.service.OrderService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import com.exam.registration.util.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname OrderController
 * @description TODO
 * @date 2019/12/18
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
}
