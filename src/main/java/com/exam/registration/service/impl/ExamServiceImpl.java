package com.exam.registration.service.impl;

import com.exam.registration.model.Exam;
import com.exam.registration.model.ExamExample;
import com.exam.registration.service.ExamService;

import java.util.List;

/**
 * @author yhf
 * @classname ExamServiceImpl
 * @description TODO
 * @date 2019/11/27
 **/
public class ExamServiceImpl implements ExamService {
    @Override
    public long countByExample(ExamExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ExamExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Exam record) {
        return 0;
    }

    @Override
    public int insertSelective(Exam record) {
        return 0;
    }

    @Override
    public List<Exam> selectByExample(ExamExample example) {
        return null;
    }

    @Override
    public Exam selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Exam record, ExamExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Exam record, ExamExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Exam record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Exam record) {
        return 0;
    }
}
