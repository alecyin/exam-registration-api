package com.exam.registration.service;

import com.exam.registration.model.Major;
import com.exam.registration.model.MajorExample;

import java.util.List;

public interface MajorService {
    long countByExample(MajorExample example);

    int deleteByExample(MajorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Major record);

    int insertSelective(Major record);

    List<Major> selectByExample(MajorExample example);

    Major selectByPrimaryKey(Long id);

    int updateByExampleSelective(Major record, MajorExample example);

    int updateByExample(Major record, MajorExample example);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
}
