package com.exam.registration.mapper;

import com.exam.registration.model.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
}