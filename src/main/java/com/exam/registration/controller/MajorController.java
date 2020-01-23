package com.exam.registration.controller;

import com.exam.registration.model.Exam;
import com.exam.registration.model.Major;
import com.exam.registration.model.Site;
import com.exam.registration.model.Subject;
import com.exam.registration.service.ExamService;
import com.exam.registration.service.MajorService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    private ExamService examService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertMajor(@RequestBody Major major) {
        if (StringUtils.isEmpty(major.getName())) {
            return MsgUtils.fail("专业名不能为空");
        }
        if (StringUtils.isEmpty(major.getCode())) {
            return MsgUtils.fail("代号不能为空");
        }
        if (StringUtils.isEmpty(major.getFee())) {
            return MsgUtils.fail("费用不能为空");
        }

        if (Objects.nonNull(majorService.getMajorByCode(major.getCode()))) {
            return MsgUtils.fail("代号重复");
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
        int res = majorService.deleteMajorByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    public String softDeleteMajor(@PathVariable("id") long id) {
        Major major = new Major();
        major.setId(id);
        major.setIsDeleted(true);
        int res = majorService.updateMajorByPrimaryKeySelective(major);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllSite(@RequestParam("ids") String ids) {
        int res = majorService.deleteMajorByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateMajor(@RequestBody Major major) {
        int res = majorService.updateMajorByPrimaryKeySelective(major);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    @RequestMapping(path = "/enabled", method = RequestMethod.GET)
    @ResponseBody
    public String listMajors() {
        List<Major> list = majorService.listMajors();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listMajorsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Major> list = majorService.listMajorsByPage(map);
        long pageTotal = majorService.countMajors(keyword);
        return MsgUtils.querySuccess(list, pageTotal);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getMajorByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(majorService.getMajorByPrimaryKey(id));
    }

    @RequestMapping(path = "/enabled-condition", method = RequestMethod.GET)
    @ResponseBody
    public String listMajorsByCondition(@RequestParam(value = "siteId", required = true) long siteId) {
        List<Exam> examList = examService.listExamsBySiteId(siteId);
        List<Major> majorList = new ArrayList<>();
        for (Exam exam : examList) {
            Major major = majorService.getMajorByPrimaryKey(exam.getMajorId());
            majorList.add(major);
        }
        return MsgUtils.success(majorList);
    }
}
