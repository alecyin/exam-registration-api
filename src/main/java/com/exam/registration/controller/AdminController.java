package com.exam.registration.controller;

import com.exam.registration.annotation.RequireRoles;
import com.exam.registration.model.Admin;
import com.exam.registration.model.Student;
import com.exam.registration.service.AdminService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname AdminController
 * @description TODO
 * @date 2019/12/17
 **/
@RequestMapping("/admins")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertAdmin(@RequestBody Admin admin) {
        if (StringUtils.isEmpty(admin.getName())) {
            return MsgUtils.fail("登录名不能为空");
        }
        if (StringUtils.isEmpty(admin.getPassword())) {
            return MsgUtils.fail("密码不能为空");
        }

        if (Objects.nonNull(adminService.getAdminByName(admin.getName()))) {
            return MsgUtils.fail("登录名重复");
        }

        int res = adminService.insertAdmin(admin);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAdmin(@PathVariable("id") long id) {
        int res = adminService.deleteAdminByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllAdmin(@RequestParam("ids") String ids) {
        int res = adminService.deleteAdminByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteAdmin(@PathVariable("id") long id) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setIsDeleted(true);
        int res = adminService.updateAdminByPrimaryKeySelective(admin);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateAdmin(@RequestBody Admin admin) {
        int res = adminService.updateAdminByPrimaryKeySelective(admin);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    public String listAdmins() {
        List<Admin> list = adminService.listAdmins();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listAdminsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam("pageIndex") int pageIndex,
                                     @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Admin> list = adminService.listAdminsByPage(map);
//        long pageTotal = (studentService.countStudents() - 1)/pageSize + 1;
        long pageTotal = adminService.countAdmins(keyword);
        return MsgUtils.querySuccess(list, pageTotal);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getAdminByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(adminService.getAdminByPrimaryKey(id));
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String getStudentByPrimaryKey(HttpServletRequest request){
        return MsgUtils.success(adminService
                .getAdminByPrimaryKey(Long.valueOf((String) request.getAttribute("adminId"))));
    }
}
