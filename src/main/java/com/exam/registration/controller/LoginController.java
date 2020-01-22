package com.exam.registration.controller;

import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Admin;
import com.exam.registration.model.Student;
import com.exam.registration.security.JwtUtil;
import com.exam.registration.service.AdminService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname LoginController
 * @description TODO
 * @date 2019/11/27
 **/
@Controller
public class LoginController {

    @Autowired
    StudentService studentService;
    @Autowired
    AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path = "/students/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginStudent(@RequestBody Student student) {
        if (StringUtils.isEmpty(student.getIdCardNumber())) {
            return MsgUtils.fail("身份证号码不能为空");
        }
        if (StringUtils.isEmpty(student.getPassword())) {
            return MsgUtils.fail("密码不能为空");
        }

        int res = studentService.login(student);
        if (res == 0) {
            return MsgUtils.fail("身份证号码或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("role","student");
        return MsgUtils.success(JwtUtil.getToken(student.getIdCardNumber(), map));
    }

    @RequestMapping(path = "/admins/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginAdmin(@RequestBody Admin admin) {
        if (StringUtils.isEmpty(admin.getName())) {
            return MsgUtils.fail("登录名不能为空");
        }
        if (StringUtils.isEmpty(admin.getPassword())) {
            return MsgUtils.fail("密码不能为空");
        }
        int res = adminService.login(admin);
        if (res == 0) {
            return MsgUtils.fail("登录名或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("role","admin");
        admin = adminService.getAdminByName(admin.getName());
        return MsgUtils.success(JwtUtil.getToken(String.valueOf(admin.getId()), map));
    }
}
