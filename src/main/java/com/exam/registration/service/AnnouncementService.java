package com.exam.registration.service;

import com.exam.registration.model.Announcement;

import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname AnnouncementService
 * @description TODO
 * @date 2019/12/17
 **/
public interface AnnouncementService {
    long countAnnouncements(String keyword);

    int deleteAnnouncementByPrimaryKey(Long id);

    int deleteAnnouncementByPrimaryKeys(String ids);

    int insertAnnouncement(Announcement record);

    Announcement getAnnouncementByPrimaryKey(Long id);

    List<Announcement> listAnnouncements();

    List<Announcement> listAnnouncementsByPage(Map<String, Object> map);

    int updateAnnouncementByPrimaryKeySelective(Announcement record);
}
