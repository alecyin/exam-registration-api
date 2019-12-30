package com.exam.registration.controller;

import com.exam.registration.model.Exam;
import com.exam.registration.model.ExamSubject;
import com.exam.registration.model.Site;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname ExamController
 * @description TODO
 * @date 2019/12/30
 **/
@RequestMapping("/exams")
@Controller
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SiteService siteService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertExam(@RequestBody Exam exam) {
        if (StringUtils.isEmpty(exam.getSiteId())) {
            return MsgUtils.fail("考点Id不能为空");
        }
        if (StringUtils.isEmpty(exam.getMajorId())) {
            return MsgUtils.fail("专业Id不能为空");
        }
        if (StringUtils.isEmpty(exam.getStartExamineeNumber())) {
            return MsgUtils.fail("起始准考证号码不能为空");
        }
        if (StringUtils.isEmpty(exam.getProvince())) {
            return MsgUtils.fail("省份不能为空");
        }
        if (StringUtils.isEmpty(exam.getStartTime())) {
            return MsgUtils.fail("开始时间不能为空");
        }
        if (StringUtils.isEmpty(exam.getEndTime())) {
            return MsgUtils.fail("结束时间不能为空");
        }

        if (Objects.nonNull(siteService.getSiteByPrimaryKey(exam.getSiteId()))) {
            return MsgUtils.fail("考点不存在");
        }
        if (Objects.nonNull(majorService.getMajorByPrimaryKey(exam.getMajorId()))) {
            return MsgUtils.fail("专业不存在");
        }

        int res = examService.insertExam(exam);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteExam(@PathVariable("id") long id) {
        int res = examService.deleteExamByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllExam(@RequestParam("ids") String ids) {
        int res = examService.deleteExamByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteExam(@PathVariable("id") long id) {
        Exam exam = new Exam();
        exam.setId(id);
        exam.setIsDeleted(true);
        int res = examService.updateExamByPrimaryKeySelective(exam);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateExam(@RequestBody Exam exam) {
        int res = examService.updateExamByPrimaryKeySelective(exam);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    public String listExams() {
        List<Exam> list = examService.listExams();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listExamsByPage(@RequestParam(value = "siteId", required = false) String siteId,
                                  @RequestParam(value = "majorId", required = false) String majorId,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("siteId", siteId);
        map.put("majorId", majorId);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Exam> list = examService.listExamsByPage(map);
        long pageTotal = examService.countExams(map);
        return MsgUtils.querySuccess(list, pageTotal);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getExamByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(examService.getExamByPrimaryKey(id));
    }
}
