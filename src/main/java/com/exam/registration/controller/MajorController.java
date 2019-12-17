package com.exam.registration.controller;

import com.exam.registration.model.Major;
import com.exam.registration.service.MajorService;
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
 * @classname MajorController
 * @description TODO
 * @date 2019/12/17
 **/
@RequestMapping("/majors")
@Controller
public class MajorController {
    @Autowired
    private MajorService majorService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertMajor(Major major) {
        if (StringUtils.isEmpty(major.getName())) {
            return MsgUtils.fail("专业名不能为空");
        }

        if (StringUtils.isEmpty(major.getCode())) {
            return MsgUtils.fail("代号不能为空");
        }

        if (StringUtils.isEmpty(major.getFee())) {
            return MsgUtils.fail("费用不能为空");
        }

        Major queryMajor = majorService.getMajorByCode(major.getCode());
        if (Objects.nonNull(queryMajor)) {
            return MsgUtils.fail("登录名重复");
        }

        int res = majorService.insertMajor(major);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMajor(@PathVariable("id") long id) {
        Major major = new Major();
        major.setId(id);
        major.setIsDeleted(true);
        int res = majorService.updateMajorByPrimaryKeySelective(major);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateMajor(Major major) {
        int res = majorService.updateMajorByPrimaryKeySelective(major);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listMajors() {
        List<Major> list = majorService.listMajors();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getMajorByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(majorService.getMajorByPrimaryKey(id));
    }
}
