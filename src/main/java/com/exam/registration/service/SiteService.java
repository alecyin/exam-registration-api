package com.exam.registration.service;

import com.exam.registration.model.Site;

import java.util.List;

/**
 * @author yhf
 * @classname SiteService
 * @description TODO
 * @date 2019/12/17
 **/
public interface SiteService {
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
