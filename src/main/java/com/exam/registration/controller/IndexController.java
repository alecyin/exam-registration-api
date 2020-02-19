package com.exam.registration.controller;

import com.exam.registration.annotation.RequireRoles;
import com.exam.registration.service.AnnouncementService;
import com.exam.registration.service.OrderService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yhf
 * @classname IndexController
 **/
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AnnouncementService announcementService;


    @RequestMapping(path = {"/count"}, method = RequestMethod.GET)
    @RequireRoles("admin")
    public String index() {
        Map<String, Object> map = new HashMap<>();
        map.put("studentNumber", studentService.countStudents(null));
        map.put("announcementNumber", announcementService.countAnnouncements(null));
        map.put("paidNumber", orderService.countPaidOrders());
        return MsgUtils.success(map);
    }

}
