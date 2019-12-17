package com.exam.registration.mapper;

import com.exam.registration.model.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SiteMapper {
    long countSites();

    int deleteSiteByPrimaryKey(Long id);

    int insertSite(Site site);

    int insertSiteSelective(Site site);

    List<Site> listSites();

    Site getSiteByPrimaryKey(Long id);

    Site getSiteByCode(String code);

    int updateSiteByPrimaryKeySelective(Site site);

    int updateSiteByPrimaryKey(Site record);
}