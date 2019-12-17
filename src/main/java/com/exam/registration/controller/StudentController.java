package com.exam.registration.controller;

import com.exam.registration.model.Student;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author yhf
 * @classname RegisteredController
 * @description TODO
 * @date 2019/11/27
 **/
@RequestMapping("/students")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String reg(Student student) {
        if (StringUtils.isEmpty(student.getIdCardNumber())) {
            return MsgUtils.fail("身份证号码不能为空");
        }
        if (StringUtils.isEmpty(student.getEmail())) {
            return MsgUtils.fail("邮箱不能为空");
        }
        Student queryStudent = studentService.getStudentByIdCardNumber(student.getIdCardNumber());
        if (Objects.nonNull(queryStudent)) {
            return MsgUtils.fail("用户名已经被注册");
        }

        int res = studentService.insertStudent(student);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteStudent(@PathVariable("id") long id) {
        Student student = new Student();
        student.setId(id);
        student.setIsDeleted(true);
        int res = studentService.updateStudentByPrimaryKeySelective(student);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateStudent(Student student) {
        int res = studentService.updateStudentByPrimaryKeySelective(student);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listStudents() {
        List<Student> list = studentService.listStudents();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudentByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(studentService.getStudentByPrimaryKey(id));
    }
}
