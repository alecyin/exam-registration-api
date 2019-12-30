package com.exam.registration.service.impl;

import com.exam.registration.mapper.AnnouncementMapper;
import com.exam.registration.model.Announcement;
import com.exam.registration.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname AnnouncementServiceImpl
 * @description TODO
 * @date 2019/12/17
 **/
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public long countAnnouncements(String keyword) {
        return announcementMapper.countAnnouncements(keyword);
    }

    @Override
    public int deleteAnnouncementByPrimaryKey(Long id) {
        return announcementMapper.deleteAnnouncementByPrimaryKey(id);
    }

    @Override
    public int deleteAnnouncementByPrimaryKeys(String ids) {
        return announcementMapper.deleteAnnouncementByPrimaryKeys(ids);
    }

    @Override
    public int insertAnnouncement(Announcement announcement) {
        Date now = new Date();
        announcement.setCreateTime(now);
        announcement.setUpdateTime(now);
        if (Objects.isNull(announcement.getIsDeleted())) {
            announcement.setIsDeleted(false);
        }
        return announcementMapper.insertAnnouncement(announcement);
    }

    @Override
    public Announcement getAnnouncementByPrimaryKey(Long id) {
        return announcementMapper.getAnnouncementByPrimaryKey(id);
    }

    @Override
    public List<Announcement> listAnnouncements() {
        return announcementMapper.listAnnouncements();
    }

    @Override
    public List<Announcement> listAnnouncementsByPage(Map<String, Object> map) {
        return announcementMapper.listAnnouncementsByPage(map);
    }

    @Override
    public int updateAnnouncementByPrimaryKeySelective(Announcement announcement) {
        announcement.setUpdateTime(new Date());
        return announcementMapper.updateAnnouncementByPrimaryKeySelective(announcement);
    }
}
