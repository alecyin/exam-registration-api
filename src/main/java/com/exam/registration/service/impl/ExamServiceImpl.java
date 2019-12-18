package com.exam.registration.service.impl;

import com.exam.registration.mapper.ExamMapper;
import com.exam.registration.model.Exam;
import com.exam.registration.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yhf
 * @classname ExamServiceImpl
 * @description TODO
 * @date 2019/12/18
 **/
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Override
    public long countExams() {
        return examMapper.countExams();
    }

    @Override
    public int deleteExamByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertExam(Exam record) {
        return 0;
    }

    @Override
    public int insertExamSelective(Exam record) {
        return 0;
    }

    @Override
    public Exam getExamByPrimaryKey(Long id) {
        return examMapper.getExamByPrimaryKey(id);
    }

    @Override
    public List<Exam> listExams() {
        return null;
    }

    @Override
    public int updateExamByPrimaryKeySelective(Exam record) {
        return 0;
    }

    @Override
    public int updateExamByPrimaryKey(Exam record) {
        return 0;
    }
}
