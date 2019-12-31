package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.Exam;
import com.exam.registration.model.ExamSubject;
import com.exam.registration.model.Site;
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
    @Autowired
    private SubjectService subjectService;

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
//        if (StringUtils.isEmpty(exam.getProvince())) {
//            return MsgUtils.fail("省份不能为空");
//        }
        if (StringUtils.isEmpty(exam.getStartTime())) {
            return MsgUtils.fail("开始时间不能为空");
        }
        if (StringUtils.isEmpty(exam.getEndTime())) {
            return MsgUtils.fail("结束时间不能为空");
        }

        if (Objects.isNull(siteService.getSiteByPrimaryKey(exam.getSiteId()))) {
            return MsgUtils.fail("考点不存在");
        }
        if (Objects.isNull(majorService.getMajorByPrimaryKey(exam.getMajorId()))) {
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

    @RequestMapping(path = "/enabled", method = RequestMethod.GET)
    @ResponseBody
    public String listExams() {
        List<Exam> list = examService.listExams();
        return MsgUtils.success(list);
    }

    public String listExamsByCondition(@RequestParam(value = "majorId", required = false) long majorId) {
        List<Exam> list;
        if (Objects.isNull(majorId)) {
            list = examService.listExams();
        } else {
            list = examService.listExamsByMajorId(majorId);
        }
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listExamsByPage(@RequestParam(value = "siteId", required = false) String siteId,
                                  @RequestParam(value = "majorId", required = false) String majorId,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("siteId", siteId);
        map.put("majorId", majorId);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Exam> list = examService.listExamsByPage(map);
        long pageTotal = examService.countExams(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("pageTotal", pageTotal);
        JSONArray jsonArray = new JSONArray();
        for (Exam exam : list) {
            StringBuilder jsonExam = new StringBuilder(JSONObject.toJSONString(exam));
            long qSiteId = exam.getSiteId();
            long qMajorId = exam.getMajorId();
            String majorName = majorService.getMajorByPrimaryKey(qMajorId).getName();
            String siteName = siteService.getSiteByPrimaryKey(qSiteId).getName();
            Map<String, Object> subMap = new HashMap<>();
            subMap.put("siteName", siteName);
            subMap.put("majorName", majorName);
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
    public String getExamByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(examService.getExamByPrimaryKey(id));
    }

    @RequestMapping(path = "/cascader",method = RequestMethod.GET)
    @ResponseBody
    public String getExamCascader() {
        List<Exam> examList = examService.listExams();
        JSONObject top = new JSONObject();
        JSONArray dataArray = new JSONArray();
        for (Exam exam : examList) {
            Map<String, Object> parMap = new HashMap<>();
            if (StringUtils.isEmpty(exam.getName())) {
                exam.setName(exam.getId().toString());
            }
            parMap.put("label", exam.getName());
            parMap.put("value", exam.getId());
            List<Subject> subjectList = subjectService.listSubjectsByMajorId(exam.getMajorId());
            JSONArray chrildArray = new JSONArray();
            for (Subject subject : subjectList) {
                Map<String, Object> chirdMap = new HashMap<>();
                chirdMap.put("label", subject.getName());
                chirdMap.put("value", subject.getId());
                chrildArray.add(chirdMap);
            }
            parMap.put("children", chrildArray);
            dataArray.add(parMap);
        }
        top.put("data", dataArray);
        top.put("code", ResCode.SUCCESS.code());
        return top.toJSONString();
    }
}
