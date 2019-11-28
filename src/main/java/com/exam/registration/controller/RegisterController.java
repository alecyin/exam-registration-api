package com.exam.registration.controller;

import com.alibaba.fastjson.JSON;
import com.exam.registration.model.Student;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname RegisteredController
 * @description TODO
 * @date 2019/11/27
 **/
@Controller
public class RegisterController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(path = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public String reg(Model model,
	                @RequestParam("idCardNumber") String idCardNumber,
	                @RequestParam("phone") String phone,
	                @RequestParam("email") String email,
	                @RequestParam("password") String password,
	                HttpServletResponse response,
	                HttpServletRequest request) {
		Student student = new Student();
		student.setPassword(password);
		student.setIdCardNumber(idCardNumber);
		student.setPhone(phone);
		student.setEmail(email);

		if (StringUtils.isEmpty(student.getIdCardNumber())) {
			return MsgUtils.fail("身份证号码不能为空");
		}
		if (StringUtils.isEmpty(student.getEmail())) {
			return MsgUtils.fail("邮箱不能为空");
		}
		Student queryStudent = studentService.selectByIdCardNumber(student.getIdCardNumber());
		if (Objects.nonNull(queryStudent)) {
			return MsgUtils.fail("用户名已经被注册");
		}

		int res = studentService.insert(student);
		if (res == 0) {
			return MsgUtils.fail("未知错误，稍后再试");
		}
		return MsgUtils.success();
	}
}
