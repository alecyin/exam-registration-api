package com.exam.registration.service.impl;

import com.exam.registration.mapper.SiteMapper;
import com.exam.registration.model.Site;
import com.exam.registration.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname SiteServiceImpl
 * @description TODO
 * @date 2019/12/17
 **/
@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteMapper siteMapper;

    @Override
    public long countSites(String keyword) {
        return siteMapper.countSites(keyword);
    }

    @Override
    public int deleteSiteByPrimaryKey(Long id) {
        return siteMapper.deleteSiteByPrimaryKey(id);
    }

    @Override
    public int deleteSiteByPrimaryKeys(String ids) {
        return siteMapper.deleteSiteByPrimaryKeys(ids);
    }

    @Override
    public int insertSite(Site site) {
        Date now = new Date();
        site.setCreateTime(now);
        site.setUpdateTime(now);
        site.setIsDeleted(false);
        return siteMapper.insertSite(site);
    }

    @Override
    public int insertSiteSelective(Site site) {
        return 0;
    }

    @Override
    public List<Site> listSites() {
        return siteMapper.listSites();
    }

    @Override
    public List<Site> listSitesByPage(Map<String, Object> map) {
        return siteMapper.listSitesByPage(map);
    }

    @Override
    public Site getSiteByPrimaryKey(Long id) {
        return siteMapper.getSiteByPrimaryKey(id);
    }

    @Override
    public Site getSiteByCode(String code) {
        return siteMapper.getSiteByCode(code);
    }

    @Override
    public int updateSiteByPrimaryKeySelective(Site site) {
        site.setUpdateTime(new Date());
        return siteMapper.updateSiteByPrimaryKeySelective(site);
    }

    @Override
    public int updateSiteByPrimaryKey(Site record) {
        return 0;
    }
}
