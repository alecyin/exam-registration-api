package com.exam.registration.service;

import com.exam.registration.model.Announcement;

import java.util.List;

/**
 * @author yhf
 * @classname AnnouncementService
 * @description TODO
 * @date 2019/12/17
 **/
public interface AnnouncementService {
    long countAnnouncements();

    int deleteAnnouncementByPrimaryKey(Long id);

    int insertAnnouncement(Announcement record);

    Announcement getAnnouncementByPrimaryKey(Long id);

    List<Announcement> listAnnouncements();

    int updateAnnouncementByPrimaryKeySelective(Announcement record);
}
