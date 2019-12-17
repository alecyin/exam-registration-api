package com.exam.registration.service.impl;

import com.exam.registration.mapper.MajorMapper;
import com.exam.registration.model.Major;
import com.exam.registration.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public long countMajors() {
        return majorMapper.countMajors();
    }

    @Override
    public int deleteMajorByPrimaryKey(Long id) {
        return majorMapper.deleteMajorByPrimaryKey(id);
    }

    @Override
    public int insertMajor(Major major) {
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
    public int updateMajorByPrimaryKeySelective(Major record) {
        return 0;
    }

    @Override
    public int updateMajorByPrimaryKey(Major record) {
        return 0;
    }
}
