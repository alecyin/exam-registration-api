package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Subject;
import com.exam.registration.service.ExamService;
import com.exam.registration.service.MajorService;
import com.exam.registration.service.SubjectService;
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
    @Autowired
    private ExamService examService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertSubject(@RequestBody Subject subject) {
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
        int res = subjectService.deleteSubjectByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllSubject(@RequestParam("ids") String ids) {
        int res = subjectService.deleteSubjectByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteSubject(@PathVariable("id") long id) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setIsDeleted(true);
        int res = subjectService.updateSubjectByPrimaryKeySelective(subject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateSubject(@RequestBody Subject subject) {
        int res = subjectService.updateSubjectByPrimaryKeySelective(subject);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(path = "/enabled", method = RequestMethod.GET)
    @ResponseBody
    public String listSubjects() {
        List<Subject> list = subjectService.listSubjects();
        return MsgUtils.success(list);
    }

    @RequestMapping(path = "/enabled-condition", method = RequestMethod.GET)
    @ResponseBody
    public String listSubjectsByCondition(@RequestParam(value = "examId", required = true) long examId) {
        long majorId = examService.getExamByPrimaryKey(examId).getMajorId();
        List<Subject> list = subjectService.listSubjectsByMajorId(majorId);
        return MsgUtils.success(list);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listSubjectsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Subject> list = subjectService.listSubjectsByPage(map);
        long pageTotal = subjectService.countSubjects(keyword);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("pageTotal", pageTotal);
        JSONArray jsonArray = new JSONArray();
        for (Subject subject : list) {
            StringBuilder jsonSub = new StringBuilder(JSONObject.toJSONString(subject));
            long majorId = subject.getMajorId();
            String majorName = majorService.getMajorByPrimaryKey(majorId).getName();
            Map<String, Object> subMap = new HashMap<>();
            subMap.put("majorId", majorId);
            subMap.put("majorName", majorName);
            String jsonSubMap = JSONObject.toJSONString(subMap);
            StringBuilder sb = new StringBuilder(jsonSubMap).deleteCharAt(0);
            jsonSub.deleteCharAt(jsonSub.length() - 1).append(",").append(sb);
            jsonArray.add(JSONObject.parse(jsonSub.toString()));
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }


    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getSubjectByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(subjectService.getSubjectByPrimaryKey(id));
    }
}
