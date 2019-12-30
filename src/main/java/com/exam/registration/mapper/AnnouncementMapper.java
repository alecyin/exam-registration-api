package com.exam.registration.mapper;

import com.exam.registration.model.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AnnouncementMapper {
    long countAnnouncements(String keyword);

    int deleteAnnouncementByPrimaryKey(Long id);

    int deleteAnnouncementByPrimaryKeys(String ids);

    int insertAnnouncement(Announcement record);

    int insertAnnouncementSelective(Announcement record);

    Announcement getAnnouncementByPrimaryKey(Long id);

    List<Announcement> listAnnouncements();

    List<Announcement> listAnnouncementsByPage(Map<String, Object> map);

    int updateAnnouncementByPrimaryKeySelective(Announcement record);

    int updateAnnouncementByPrimaryKeyWithBLOBs(Announcement record);

    int updateAnnouncementByPrimaryKey(Announcement record);
}