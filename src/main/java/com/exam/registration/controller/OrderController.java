package com.exam.registration.controller;

import com.exam.registration.model.Order;
import com.exam.registration.service.ExamService;
import com.exam.registration.service.OrderService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    public String insertOrder(Order order) {
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

        if (Objects.nonNull(studentService.getStudentByPrimaryKey(order.getStudentId()))) {
            return MsgUtils.fail("学生不存在");
        }
        if (Objects.nonNull(examService.getExamByPrimaryKey(order.getExamId()))) {
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
        Order order = new Order();
        order.setId(id);
        order.setIsDeleted(true);
        int res = orderService.updateOrderByPrimaryKeySelective(order);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateOrder(Order order) {
        int res = orderService.updateOrderByPrimaryKeySelective(order);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listOrders() {
        List<Order> list = orderService.listOrders();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getOrderByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(orderService.getOrderByPrimaryKey(id));
    }
}
