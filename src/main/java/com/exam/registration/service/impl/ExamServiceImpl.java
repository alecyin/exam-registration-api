package com.exam.registration.service.impl;

import com.exam.registration.mapper.ExamMapper;
import com.exam.registration.model.Exam;
import com.exam.registration.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        return examMapper.deleteExamByPrimaryKey(id);
    }

    @Override
    public int insertExam(Exam exam) {
        return examMapper.insertExam(exam);
    }

    @Override
    public int insertExamSelective(Exam exam) {
        Date now = new Date();
        exam.setCreateTime(now);
        exam.setUpdateTime(now);
        if (Objects.isNull(exam.getIsDeleted())) {
            exam.setIsDeleted(false);
        }
        return examMapper.insertExamSelective(exam);
    }

    @Override
    public Exam getExamByPrimaryKey(Long id) {
        return examMapper.getExamByPrimaryKey(id);
    }

    @Override
    public List<Exam> listExams() {
        return examMapper.listExams();
    }

    @Override
    public int updateExamByPrimaryKeySelective(Exam exam) {
        exam.setUpdateTime(new Date());
        return examMapper.updateExamByPrimaryKeySelective(exam);
    }

    @Override
    public int updateExamByPrimaryKey(Exam record) {
        return 0;
    }
}
