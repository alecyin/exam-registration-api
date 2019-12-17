package com.exam.registration.controller;

import com.exam.registration.model.Admin;
import com.exam.registration.service.AdminService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    public String insertAdmin(Admin admin) {
        if (StringUtils.isEmpty(admin.getName())) {
            return MsgUtils.fail("登录名不能为空");
        }

        if (StringUtils.isEmpty(admin.getPassword())) {
            return MsgUtils.fail("密码不能为空");
        }

        Admin queryAdmin = adminService.getAdminByName(admin.getName());
        if (Objects.nonNull(queryAdmin)) {
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
        Admin admin = new Admin();
        admin.setId(id);
        admin.setIsDeleted(true);
        int res = adminService.updateAdminByPrimaryKeySelective(admin);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateAdmin(Admin admin) {
        int res = adminService.updateAdminByPrimaryKeySelective(admin);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listAdmins() {
        List<Admin> list = adminService.listAdmins();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getAdminByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(adminService.getAdminByPrimaryKey(id));
    }
}
