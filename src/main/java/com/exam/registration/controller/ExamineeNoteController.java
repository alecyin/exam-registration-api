package com.exam.registration.controller;

import com.exam.registration.model.ExamineeNote;
import com.exam.registration.service.ExamineeNoteService;
import com.exam.registration.util.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yhf
 * @classname ExamineeNoteController
 * @description TODO
 * @date 2019/12/17
 **/
@RequestMapping("/examinee-notes")
@Controller
public class ExamineeNoteController {
    @Autowired
    private ExamineeNoteService examineeNoteService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public String insertOrUpdateExamineeNote(@RequestBody ExamineeNote examineeNote) {
        int res;
//        if (!StringUtils.isEmpty(content)) {
//            StringBuilder sb = new StringBuilder(content);
//            content = sb.deleteCharAt(sb.length() - 1).deleteCharAt(0).toString();
//        }
//        ExamineeNote examineeNote = new ExamineeNote();
//        examineeNote.setContent(content);
        if (examineeNoteService.isExisted()) {
            res = examineeNoteService.updateExamineeNote(examineeNote);
        } else {
            res = examineeNoteService.insertExamineeNote(examineeNote);
        }

        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(method = {RequestMethod.GET})
    @ResponseBody
    public String getExamineeNote() {
        ExamineeNote examineeNote = null;
        if (examineeNoteService.isExisted()) {
            examineeNote = examineeNoteService.getExamineeNote();
        }
        return MsgUtils.success(examineeNote);
    }
}
