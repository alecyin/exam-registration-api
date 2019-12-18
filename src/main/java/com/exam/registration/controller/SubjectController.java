package com.exam.registration.controller;

import com.exam.registration.model.Subject;
import com.exam.registration.service.MajorService;
import com.exam.registration.service.SubjectService;
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
 * @classname SubjectController
 * @description TODO
 * @date 2019/12/18
 **/
@RequestMapping("/subjects")
@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private MajorService majorService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertSubject(Subject subject) {
        if (StringUtils.isEmpty(subject.getName())) {
            return MsgUtils.fail("科目名不能为空");
        }
        if (StringUtils.isEmpty(subject.getCode())) {
            return MsgUtils.fail("代号不能为空");
        }
        if (StringUtils.isEmpty(subject.getType())) {
            return MsgUtils.fail("考试形式不能为空");
        }
        if (StringUtils.isEmpty(subject.getMajorId())) {
            return MsgUtils.fail("专业id不能为空");
        }

        if (Objects.isNull(majorService.getMajorByPrimaryKey(subject.getMajorId()))) {
            return MsgUtils.fail("专业不存在");
        }
        if (Objects.nonNull(subjectService.getSubjectByCode(subject.getCode()))) {
            return MsgUtils.fail("代号重复");
        }

        int res = subjectService.insertSubject(subject);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteSubject(@PathVariable("id") long id) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setIsDeleted(true);
        int res = subjectService.updateSubjectByPrimaryKeySelective(subject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateSubject(Subject subject) {
        int res = subjectService.updateSubjectByPrimaryKeySelective(subject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listSubjects() {
        List<Subject> list = subjectService.listSubjects();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getSubjectByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(subjectService.getSubjectByPrimaryKey(id));
    }
}
