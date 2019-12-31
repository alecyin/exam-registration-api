package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Exam;
import com.exam.registration.model.ExamSubject;
import com.exam.registration.model.Subject;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import com.exam.registration.util.ResCode;
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
    @Autowired
    private MajorService majorService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertExamSubject(@RequestBody ExamSubject examSubject) {
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
        int res = examSubjectService.deleteExamSubjectByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllExamSubject(@RequestParam("ids") String ids) {
        int res = examSubjectService.deleteExamSubjectByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteExamSubject(@PathVariable("id") long id) {
        ExamSubject examSubject = new ExamSubject();
        examSubject.setId(id);
        examSubject.setIsDeleted(true);
        int res = examSubjectService.updateExamSubjectByPrimaryKeySelective(examSubject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateExamSubject(@RequestBody ExamSubject examSubject) {
        int res = examSubjectService.updateExamSubjectByPrimaryKeySelective(examSubject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(path = "/enabled", method = RequestMethod.GET)
    @ResponseBody
    public String listExamSubjects() {
        List<ExamSubject> list = examSubjectService.listExamSubjects();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listExamsByPage(@RequestParam(value = "examId", required = false) String examId,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("examId", examId);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<ExamSubject> list = examSubjectService.listExamSubjectsByPage(map);
        long pageTotal = examSubjectService.countExamSubjects(map);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("pageTotal", pageTotal);
        JSONArray jsonArray = new JSONArray();
        for (ExamSubject examSubject : list) {
            StringBuilder jsonExam = new StringBuilder(JSONObject.toJSONString(examSubject));
            long qSubjectId = examSubject.getSubjectId();
            Subject subject = subjectService.getSubjectByPrimaryKey(qSubjectId);
            String subjectName = subject.getName();
            String majorName = majorService.getMajorByPrimaryKey(subject.getMajorId()).getName();
            String examName = examService.getExamByPrimaryKey(examSubject.getExamId()).getName();
            Map<String, Object> subMap = new HashMap<>();
            subMap.put("subjectName", subjectName);
            subMap.put("majorName", majorName);
            subMap.put("examName", examName);
            String jsonSubMap = JSONObject.toJSONString(subMap);
            StringBuilder sb = new StringBuilder(jsonSubMap).deleteCharAt(0);
            jsonExam.deleteCharAt(jsonExam.length() - 1).append(",").append(sb);
            jsonArray.add(JSONObject.parse(jsonExam.toString()));
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getExamSubjectByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(examSubjectService.getExamSubjectByPrimaryKey(id));
    }
}
