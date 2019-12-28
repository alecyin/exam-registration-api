package com.exam.registration.mapper;

import com.exam.registration.model.Site;
import com.exam.registration.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SiteMapper {
    long countSites(String keyword);

    int deleteSiteByPrimaryKey(Long id);

    int deleteSiteByPrimaryKeys(String ids);

    int insertSite(Site site);

    int insertSiteSelective(Site site);

    List<Site> listSites();

    List<Site> listSitesByPage(Map<String, Object> map);

    Site getSiteByPrimaryKey(Long id);

    Site getSiteByCode(String code);

    int updateSiteByPrimaryKeySelective(Site site);

    int updateSiteByPrimaryKey(Site record);
}