package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.exam.registration.annotation.RequireRoles;
import com.exam.registration.model.Exam;
import com.exam.registration.model.Major;
import com.exam.registration.model.Order;
import com.exam.registration.model.Site;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.*;

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
        JSONArray timesArray = new JSONArray();
        JSONArray siteNameArray = new JSONArray();
        for (Site site : siteList) {
            Long siteId = site.getId();
            int times = 0;
            siteNameArray.add(site.getName());
            for (Order order : orderList) {
                Exam exam = examService.getExamByPrimaryKey(order.getExamId());
                if (exam.getSiteId() == siteId) {
                    times++;
                }
            }
            timesArray.add(times);
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(siteNameArray);
        jsonArray.add(timesArray);
        return MsgUtils.success(jsonArray);
    }

    @RequestMapping(path = {"/percent"}, method = RequestMethod.GET)
    @RequireRoles("admin")
    @ResponseBody
    public String percent() {
        List<Major> majorList = majorService.listMajors();
        List<Order> orderList = orderService.listPaidOrders();
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (Major major : majorList) {
            Long majorId = major.getId();
            int times = 0;
            for (Order order : orderList) {
                Exam exam = examService.getExamByPrimaryKey(order.getExamId());
                if (exam.getMajorId() == majorId) {
                    times++;
                }
            }
            treeMap.put(major.getName(), times);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(treeMap.entrySet());
        //降序排序
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<String, Integer> e: list) {
            JSONArray jsonArray1 = new JSONArray();
            jsonArray1.add(e.getKey());
            DecimalFormat df = new DecimalFormat("0.00");
            String percent = df.format((float)e.getValue()/orderList.size());
            jsonArray1.add(percent);
            jsonArray.add(jsonArray1);
        }
        return MsgUtils.success(jsonArray);
    }
}
