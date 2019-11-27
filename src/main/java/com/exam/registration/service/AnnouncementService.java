package com.exam.registration.service;

import com.exam.registration.model.Announcement;
import com.exam.registration.model.AnnouncementExample;

import java.util.List;

public interface AnnouncementService {
    long countByExample(AnnouncementExample example);

    int deleteByExample(AnnouncementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    List<Announcement> selectByExampleWithBLOBs(AnnouncementExample example);

    List<Announcement> selectByExample(AnnouncementExample example);

    Announcement selectByPrimaryKey(Long id);

    int updateByExampleSelective(Announcement record, AnnouncementExample example);

    int updateByExampleWithBLOBs(Announcement record, AnnouncementExample example);

    int updateByExample(Announcement record, AnnouncementExample example);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKeyWithBLOBs(Announcement record);

    int updateByPrimaryKey(Announcement record);
}
