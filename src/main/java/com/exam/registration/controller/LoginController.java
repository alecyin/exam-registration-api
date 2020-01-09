package com.exam.registration.controller;

import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Admin;
import com.exam.registration.model.AdminConvert;
import com.exam.registration.model.Student;
import com.exam.registration.service.AdminService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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

    @RequestMapping(path = "/students/login",
            method = RequestMethod.POST)
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
        // 生成令牌,主要是用它生成载荷
        JwtBuilder builder = Jwts.builder()
                // 设置头部,使用hs256加密, + key,也就是盐
                .signWith(SignatureAlgorithm.HS256, "ceshi")
                // 添加载荷
                .setId(String.valueOf(student.getId())) // 用户id
                .setSubject(student.getName()) // 用户名
                .setExpiration(new Date(new Date().getTime() + 60*1000)) // 过期时间
                .setIssuedAt(new Date())// 登录时间
                // 添加自定义的键值对
                .claim("role","student");
        System.out.println(builder.compact());
        return MsgUtils.success(builder.compact());
    }

    @RequestMapping(path = "/admins/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginAdmin(@RequestBody AdminConvert adminConvert) {
        Admin admin = adminConvert.getAdmin();
        logger.info("get parm username :{}, password : {}", admin.getName(), admin.getPassword());
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
        // 生成令牌,主要是用它生成载荷
        JwtBuilder builder = Jwts.builder()
                // 设置头部,使用hs256加密, + key,也就是盐
                .signWith(SignatureAlgorithm.HS256, "ceshi")
                // 添加载荷
                .setId(String.valueOf(admin.getId())) // 用户id
                .setSubject(admin.getName()) // 用户名
                .setExpiration(new Date(new Date().getTime() + 60*1000)) // 过期时间
                .setIssuedAt(new Date())// 登录时间
                // 添加自定义的键值对
                .claim("role","admin");
        System.out.println(builder.compact());
        JSONObject data = new JSONObject();
        data.put("token", admin.getName());
        return MsgUtils.success(builder.compact());
    }

    @RequestMapping(path = "/admins/info", method = RequestMethod.GET)
    @ResponseBody
    public String getAdminInfo(@RequestParam String token) {
        logger.info("get token :{}", token);
        if (StringUtils.isEmpty(token)) {
            return MsgUtils.fail("token不能为空");
        }

        Admin admin = adminService.getAdminByName(token);

        if (Objects.isNull(admin)) {
            return MsgUtils.fail("token不存在");
        }
        // 先使用name调试。。
        JSONObject data = new JSONObject();
        data.put("roles", "admin");
        data.put("introduction", "I am a super administrator");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", admin.getName());
        return MsgUtils.success(data);
    }
}
