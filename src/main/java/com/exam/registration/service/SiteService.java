package com.exam.registration.service;

import com.exam.registration.model.Site;

import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname SiteService
 * @description TODO
 * @date 2019/12/17
 **/
public interface SiteService {
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
