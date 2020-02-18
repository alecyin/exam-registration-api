package com.exam.registration.controller;

import com.alibaba.fastjson.JSONObject;
import com.exam.registration.annotation.RequireRoles;
import com.exam.registration.model.Student;
import com.exam.registration.security.JwtUtil;
import com.exam.registration.service.StudentService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author yhf
 * @classname RegisteredController
 **/
@RequestMapping("/students")
@Controller
public class StudentController {

    @Value("${photo.upload.path}")
    private String path;
    @Value("${photo.upload.suffix}")
    private String suffix;

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String reg(Student student) {
        if (StringUtils.isEmpty(student.getIdCardNumber())) {
            return MsgUtils.fail("身份证号码不能为空");
        }
//        if (StringUtils.isEmpty(student.getEmail())) {
//            return MsgUtils.fail("邮箱不能为空");
//        }
        if (StringUtils.isEmpty(student.getPassword())) {
            return MsgUtils.fail("密码不能为空");
        }
        if (Objects.nonNull(studentService.getStudentByIdCardNumber(student.getIdCardNumber()))) {
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
    @RequireRoles("admin")
    public String deleteStudent(@PathVariable("id") long id) {
        int res = studentService.deleteStudentByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @RequireRoles("admin")
    public String deleteAllStudent(@RequestParam("ids") String ids) {
        int res = studentService.deleteStudentByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteStudent(@PathVariable("id") long id) {
        Student student = new Student();
        student.setId(id);
        student.setIsDeleted(true);
        int res = studentService.updateStudentByPrimaryKeySelective(student);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateStudent(@RequestBody Student student) {
        int res = studentService.updateStudentByPrimaryKeySelective(student);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }


    public String listStudents() {
        List<Student> list = studentService.listStudents();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @RequireRoles("admin")
    public String listStudentsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam("pageIndex") int pageIndex,
                                     @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Student> list = studentService.listStudentsByPage(map);
//        long pageTotal = (studentService.countStudents() - 1)/pageSize + 1;
        long pageTotal = studentService.countStudents(keyword);
        return MsgUtils.querySuccess(list, pageTotal);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudentByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(studentService.getStudentByPrimaryKey(id));
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String getStudentByPrimaryKey(HttpServletRequest request){
        return MsgUtils.success(studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId"))));
    }

    @RequestMapping(path = "/pic", method = RequestMethod.POST)
    @ResponseBody
    @RequireRoles("student")
    public String getStudentPicByPrimaryKey(@RequestBody Map<String, Object> map,
                                         HttpServletRequest request) throws ServletException {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        if (!student.getIdCardNumber().equals(map.get("idCardNumber"))) {
            return MsgUtils.fail("访问错误");
        }
        Map<String, Object> pic = new HashMap<>();
        pic.put("idCardPic", student.getIdCardPic() + suffix);
        pic.put("profilePic", student.getProfilePic() + suffix);
        pic.put("provincialExamineePic", student.getProvincialExamineePic() + suffix);
        return MsgUtils.success(pic);
    }

    @RequestMapping(path = "/update-pass", method = RequestMethod.POST)
    @ResponseBody
    public String updatePass(@RequestBody Map<String, Object> map,
                                            HttpServletRequest request) throws ServletException {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        String oldPass = (String) map.get("oldPass");
        String newPass = (String) map.get("newPass");
        String confirmPass = (String) map.get("confirmPass");
        if (!newPass.equals(confirmPass)) {
            return MsgUtils.fail("两次密码输入不一致！");
        }
        if (StringUtils.isEmpty(oldPass)) {
            return MsgUtils.fail("原密码不能为空！");
        }
        if (!DigestUtils.md5DigestAsHex((oldPass + student.getSalt()).getBytes())
                .equals(student.getPassword())) {
            return MsgUtils.fail("原密码错误！");
        }
        student.setPassword(newPass);
        return studentService.updateStudentByPrimaryKeySelective(student) == 1 ? MsgUtils.success()
                                : MsgUtils.fail("请稍后再试");
    }

    @RequestMapping("/upload/{type}")
    @ResponseBody
    public String uploadPortrait(@PathVariable("type") int type,
                                 @RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) throws IOException {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        String idCardNumber = student.getIdCardNumber();
        //保存的文件名
        String dFileName = UUID.randomUUID().toString()
                                .replace("-", "");
        //生成保存文件
        File uploadFile = new File(path + dFileName + suffix);
        //将上传文件保存到路径
        file.transferTo(uploadFile);
        String oldPicPath = null;
        if (type == 1) {// 上传的是身份证图片
            if (!StringUtils.isEmpty(student.getIdCardPic())) {// 如果之前存在文件，则需要删除之前的文件
                oldPicPath = path + student.getIdCardPic() + suffix;
            }
            student.setIdCardPic(dFileName);
        } else if (type == 2) {// 上传的是手持身份证图片
            if (!StringUtils.isEmpty(student.getProfilePic())) {
                oldPicPath = path + student.getProfilePic() + suffix;
            }
            student.setProfilePic(dFileName);
        } else if (type == 3) {// 上传的是省准考证图片
            if (!StringUtils.isEmpty(student.getProvincialExamineePic())) {
                oldPicPath = path + student.getProvincialExamineePic() + suffix;
            }
            student.setProvincialExamineePic(dFileName);
        }

        if (!StringUtils.isEmpty(oldPicPath)) {
            new File(oldPicPath).delete();
        }

        return studentService.updateStudentByPrimaryKeySelective(student) == 1 ?
                MsgUtils.success("保存成功") : MsgUtils.fail("保存失败");
    }
}
