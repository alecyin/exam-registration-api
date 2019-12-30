package com.exam.registration.service.impl;

import com.exam.registration.mapper.MajorMapper;
import com.exam.registration.model.Major;
import com.exam.registration.model.Site;
import com.exam.registration.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname MajorServiceImpl
 * @description TODO
 * @date 2019/12/17
 **/
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public long countMajors(String keyword) {
        return majorMapper.countMajors(keyword);
    }

    @Override
    public int deleteMajorByPrimaryKey(Long id) {
        return majorMapper.deleteMajorByPrimaryKey(id);
    }

    @Override
    public int deleteMajorByPrimaryKeys(String ids) {
        return majorMapper.deleteMajorByPrimaryKeys(ids);
    }

    @Override
    public int insertMajor(Major major) {
        Date now = new Date();
        major.setCreateTime(now);
        major.setUpdateTime(now);
        if (Objects.isNull(major.getIsDeleted())) {
            major.setIsDeleted(false);
        }
        return majorMapper.insertMajor(major);
    }

    @Override
    public int insertMajorSelective(Major major) {
        return 0;
    }

    @Override
    public Major getMajorByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Major getMajorByCode(String code) {
        return null;
    }

    @Override
    public List<Major> listMajors() {
        return majorMapper.listMajors();
    }

    @Override
    public List<Major> listMajorsByPage(Map<String, Object> map) {
        return majorMapper.listMajorsByPage(map);
    }

    @Override
    public int updateMajorByPrimaryKeySelective(Major major) {
        major.setUpdateTime(new Date());
        return majorMapper.updateMajorByPrimaryKeySelective(major);
    }

    @Override
    public int updateMajorByPrimaryKey(Major record) {
        return 0;
    }
}
