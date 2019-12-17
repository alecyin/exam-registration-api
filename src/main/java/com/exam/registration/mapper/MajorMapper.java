package com.exam.registration.mapper;

import com.exam.registration.model.Major;

import java.util.List;

public interface MajorMapper {
    long countMajors();

    int deleteMajorByPrimaryKey(Long id);

    int insertMajor(Major record);

    int insertMajorSelective(Major record);

    Major selectMajorByPrimaryKey(Long id);

    List<Major> listMajors();

    int updateMajorByPrimaryKeySelective(Major record);

    int updateMajorByPrimaryKey(Major record);
}