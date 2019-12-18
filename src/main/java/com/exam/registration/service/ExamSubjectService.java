package com.exam.registration.service;

import com.exam.registration.model.ExamSubject;

import java.util.List;

/**
 * @author yhf
 * @classname ExamSubjectService
 * @description TODO
 * @date 2019/12/18
 **/
public interface ExamSubjectService {
    long countExamSubjects();

    int deleteExamSubjectByPrimaryKey(Long id);

    int insertExamSubject(ExamSubject examSubject);

    int insertExamSubjectSelective(ExamSubject examSubject);

    ExamSubject getExamSubjectByPrimaryKey(Long id);

    List<ExamSubject> listExamSubjects();

    int updateExamSubjectByPrimaryKeySelective(ExamSubject examSubject);

    int updateExamSubjectByPrimaryKey(ExamSubject examSubject);
}
