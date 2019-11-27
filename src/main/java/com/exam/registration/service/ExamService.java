package com.exam.registration.service;

import com.exam.registration.model.Exam;
import com.exam.registration.model.ExamExample;

import java.util.List;

public interface ExamService {
    long countByExample(ExamExample example);

    int deleteByExample(ExamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    int insertSelective(Exam record);

    List<Exam> selectByExample(ExamExample example);

    Exam selectByPrimaryKey(Long id);

    int updateByExampleSelective(Exam record, ExamExample example);

    int updateByExample(Exam record, ExamExample example);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}
