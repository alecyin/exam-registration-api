package com.exam.registration.service;

import com.exam.registration.model.Site;
import com.exam.registration.model.SiteExample;

import java.util.List;

public interface SiteService {
    long countByExample(SiteExample example);

    int deleteByExample(SiteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Site record);

    int insertSelective(Site record);

    List<Site> selectByExample(SiteExample example);

    Site selectByPrimaryKey(Long id);

    int updateByExampleSelective(Site record, SiteExample example);

    int updateByExample(Site record, SiteExample example);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
}
