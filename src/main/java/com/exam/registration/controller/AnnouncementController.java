package com.exam.registration.controller;

import com.exam.registration.model.Announcement;
import com.exam.registration.model.Site;
import com.exam.registration.service.AnnouncementService;
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
 * @classname AnnouncementController
 * @description TODO
 * @date 2019/12/17
 **/
@RequestMapping("/announcements")
@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insertAnnouncement(@RequestBody Announcement announcement) {
        if (StringUtils.isEmpty(announcement.getTitle())) {
            return MsgUtils.fail("标题不能为空");
        }
        if (StringUtils.isEmpty(announcement.getContent())) {
            return MsgUtils.fail("内容不能为空");
        }

        int res = announcementService.insertAnnouncement(announcement);
        if (res == 0) {
            return MsgUtils.fail("未知错误，稍后再试");
        }
        return MsgUtils.success();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAnnouncement(@PathVariable("id") long id) {
        int res = announcementService.deleteAnnouncementByPrimaryKey(id);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllAnnouncement(@RequestParam("ids") String ids) {
        int res = announcementService.deleteAnnouncementByPrimaryKeys(ids);
        return res == 0 ? MsgUtils.fail("删除失败，稍后再试") : MsgUtils.success();
    }

    public String softDeleteAnnouncement(@PathVariable("id") long id) {
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement.setIsDeleted(true);
        int res = announcementService.updateAnnouncementByPrimaryKeySelective(announcement);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("删除失败，稍后再试");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateAnnouncement(@RequestBody Announcement announcement) {
        int res = announcementService.updateAnnouncementByPrimaryKeySelective(announcement);
        return res == 1 ? MsgUtils.success() : MsgUtils.fail("修改失败，稍后再试");
    }

    public String listAnnouncements() {
        List<Announcement> list = announcementService.listAnnouncements();
        return MsgUtils.success(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listAnnouncementsByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize) {
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            return MsgUtils.fail("缺少参数");
        }

        Map<String, Object> map = new HashMap<>(4);
        map.put("keyword", keyword);
        map.put("currentIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Announcement> list = announcementService.listAnnouncementsByPage(map);
        long pageTotal = announcementService.countAnnouncements(keyword);
        return MsgUtils.querySuccess(list, pageTotal);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getAnnouncementByPrimaryKey(@PathVariable("id") long id) {
        return MsgUtils.success(announcementService.getAnnouncementByPrimaryKey(id));
    }
}
