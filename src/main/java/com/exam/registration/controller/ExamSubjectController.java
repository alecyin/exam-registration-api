package com.exam.registration.controller;

import com.exam.registration.model.ExamSubject;
import com.exam.registration.service.ExamService;
import com.exam.registration.service.ExamSubjectService;
import com.exam.registration.service.SiteService;
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
 * @classname ExamSubjectController
 * @description TODO
 * @date 2019/12/18
 **/
@RequestMapping("/exam-subjects")
@Controller
public class ExamSubjectController {
    @Autowired
    private ExamSubjectService examSubjectService;
    @Autowired
    private ExamService examService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SiteService siteService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertExamSubject(ExamSubject examSubject) {
        if (StringUtils.isEmpty(examSubject.getExamId())) {
            return MsgUtils.fail("专业id不能为空");
        }
        if (StringUtils.isEmpty(examSubject.getAddress())) {
            return MsgUtils.fail("地址不能为空");
        }
        if (StringUtils.isEmpty(examSubject.getSubjectId())) {
            return MsgUtils.fail("科目id不能为空");
        }
        if (Objects.isNull(examSubject.getStartTime())) {
            return MsgUtils.fail("开始时间不能为空");
        }
        if (Objects.isNull(examSubject.getEndTime())) {
            return MsgUtils.fail("结束时间不能为空");
        }

        if (Objects.isNull(examService.getExamByPrimaryKey(examSubject.getExamId()))) {
            return MsgUtils.fail("专业不存在");
        }
        if (Objects.isNull(subjectService.getSubjectByPrimaryKey(examSubject.getSubjectId()))) {
            return MsgUtils.fail("科目不存在");
        }

        int res = examSubjectService.insertExamSubject(examSubject);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteExamSubject(@PathVariable("id") long id) {
        ExamSubject examSubject = new ExamSubject();
        examSubject.setId(id);
        examSubject.setIsDeleted(true);
        int res = examSubjectService.updateExamSubjectByPrimaryKeySelective(examSubject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateExamSubject(ExamSubject examSubject) {
        int res = examSubjectService.updateExamSubjectByPrimaryKeySelective(examSubject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listExamSubjects() {
        List<ExamSubject> list = examSubjectService.listExamSubjects();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getExamSubjectByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(examSubjectService.getExamSubjectByPrimaryKey(id));
    }
}
