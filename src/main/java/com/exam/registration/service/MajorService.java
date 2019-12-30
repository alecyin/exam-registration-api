package com.exam.registration.service;

import com.exam.registration.model.Major;
import com.exam.registration.model.Site;

import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname MajorService
 * @description TODO
 * @date 2019/12/17
 **/
public interface MajorService {
    long countMajors(String keyword);

    int deleteMajorByPrimaryKey(Long id);

    int deleteMajorByPrimaryKeys(String ids);

    int insertMajor(Major record);

    int insertMajorSelective(Major record);

    Major getMajorByPrimaryKey(Long id);

    Major getMajorByCode(String code);

    List<Major> listMajors();

    List<Major> listMajorsByPage(Map<String, Object> map);

    int updateMajorByPrimaryKeySelective(Major record);

    int updateMajorByPrimaryKey(Major record);
}
