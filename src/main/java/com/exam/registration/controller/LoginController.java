package com.exam.registration.controller;

import com.exam.registration.service.AdminService;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(path = "/login/student", method = RequestMethod.POST)
	@ResponseBody
	public String loginStudent(@RequestParam("idCardNumber") String idCardNumber,
	                    @RequestParam("password") String password) {
		if (StringUtils.isEmpty(idCardNumber)) {
			return MsgUtils.fail("身份证号码不能为空");
		}
		if (StringUtils.isEmpty(password)) {
			return MsgUtils.fail("密码不能为空");
		}

		int res = studentService.login(idCardNumber, password);
		if (res == 0) {
			return MsgUtils.fail("身份证号码或密码错误");
		}
		return MsgUtils.success();
	}

	@RequestMapping(path = "/login/admin", method = RequestMethod.POST)
	@ResponseBody
	public String loginAdmin(@RequestParam("name") String name,
	                    @RequestParam("password") String password) {
		if (StringUtils.isEmpty(name)) {
			return MsgUtils.fail("登录名不能为空");
		}
		if (StringUtils.isEmpty(password)) {
			return MsgUtils.fail("密码不能为空");
		}

		int res = studentService.login(name, password);
		if (res == 0) {
			return MsgUtils.fail("登录名或密码错误");
		}
		return MsgUtils.success();
	}
}
