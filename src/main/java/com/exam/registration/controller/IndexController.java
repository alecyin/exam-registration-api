package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.exam.registration.annotation.RequireRoles;
import com.exam.registration.model.Exam;
import com.exam.registration.model.Order;
import com.exam.registration.model.Site;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private SiteService siteService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ExamService examService;


    @RequestMapping(path = {"/count"}, method = RequestMethod.GET)
    @RequireRoles("admin")
    @ResponseBody
    public String index() {
        Map<String, Object> map = new HashMap<>();
        map.put("studentNumber", studentService.countStudents(null));
        map.put("announcementNumber", announcementService.countAnnouncements(null));
        map.put("paidNumber", orderService.countPaidOrders());
        return MsgUtils.success(map);
    }

    @RequestMapping(path = {"/site"}, method = RequestMethod.GET)
    @RequireRoles("admin")
    @ResponseBody
    public String site() {
        List<Site> siteList = siteService.listSites();
        List<Order> orderList = orderService.listPaidOrders();
        List<Integer> timesList = new ArrayList<>();
        for (Site site : siteList) {
            Long siteId = site.getId();
            int times = 0;
            for (Order order : orderList) {
                Exam exam = examService.getExamByPrimaryKey(order.getExamId());
                if (exam.getSiteId() == siteId) {
                    times++;
                }
            }
            timesList.add(times);
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(siteList);
        jsonArray.add(timesList);
        return MsgUtils.success(jsonArray);
    }
}
