package com.exam.registration.mapper;

import com.exam.registration.model.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnnouncementMapper {
    long countAnnouncements();

    int deleteAnnouncementByPrimaryKey(Long id);

    int insertAnnouncement(Announcement record);

    int insertAnnouncementSelective(Announcement record);

    Announcement getAnnouncementByPrimaryKey(Long id);

    List<Announcement> listAnnouncements();

    int updateAnnouncementByPrimaryKeySelective(Announcement record);

    int updateAnnouncementByPrimaryKeyWithBLOBs(Announcement record);

    int updateAnnouncementByPrimaryKey(Announcement record);
}