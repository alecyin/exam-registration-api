package com.exam.registration.service;

import com.exam.registration.model.ExamSubject;
import com.exam.registration.model.ExamSubjectExample;

import java.util.List;

public interface ExamSubjectService {
    long countByExample(ExamSubjectExample example);

    int deleteByExample(ExamSubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamSubject record);

    int insertSelective(ExamSubject record);

    List<ExamSubject> selectByExample(ExamSubjectExample example);

    ExamSubject selectByPrimaryKey(Long id);

    int updateByExampleSelective(ExamSubject record, ExamSubjectExample example);

    int updateByExample(ExamSubject record, ExamSubjectExample example);

    int updateByPrimaryKeySelective(ExamSubject record);

    int updateByPrimaryKey(ExamSubject record);
}
