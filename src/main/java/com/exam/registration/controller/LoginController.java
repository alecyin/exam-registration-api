package com.exam.registration.controller;

import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Admin;
import com.exam.registration.model.Student;
import com.exam.registration.security.JwtUtil;
import com.exam.registration.service.AdminService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import com.exam.registration.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author yhf
 * @classname LoginController
 **/
@Controller
public class LoginController {

    @Autowired
    StudentService studentService;
    @Autowired
    AdminService adminService;
    private RedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();//序列化为String
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

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
        student = studentService.getStudentByIdCardNumber(student.getIdCardNumber());
        map.put("role","student");
        String token = JwtUtil.getToken(String.valueOf(student.getId()), map);
        redisTemplate.opsForValue().set(RedisUtils.STUDENT_TOKEN_PREFIX + student.getId(), token,
                                                                JwtUtil.TOKEN_EXP, TimeUnit.MILLISECONDS);
        return MsgUtils.success(token);
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
        String token = JwtUtil.getToken(String.valueOf(admin.getId()), map);
        redisTemplate.opsForValue().set(RedisUtils.ADMIN_TOKEN_PREFIX + admin.getId(),
                                                    token,JwtUtil.TOKEN_EXP, TimeUnit.MILLISECONDS);
        return MsgUtils.success(JwtUtil.getToken(String.valueOf(admin.getId()), map));
    }
}
