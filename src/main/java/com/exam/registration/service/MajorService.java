package com.exam.registration.service;

import com.exam.registration.model.Major;

import java.util.List;

/**
 * @author yhf
 * @classname MajorService
 * @description TODO
 * @date 2019/12/17
 **/
public interface MajorService {
    long countMajors();

    int deleteMajorByPrimaryKey(Long id);

    int insertMajor(Major record);

    int insertMajorSelective(Major record);

    Major getMajorByPrimaryKey(Long id);

    Major getMajorByCode(String code);

    List<Major> listMajors();

    int updateMajorByPrimaryKeySelective(Major record);

    int updateMajorByPrimaryKey(Major record);
}
