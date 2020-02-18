package com.exam.registration.service;

import com.exam.registration.model.ExamSubject;

import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname ExamSubjectService
 * @description TODO
 * @date 2019/12/18
 **/
public interface ExamSubjectService {
    long countExamSubjects(Map<String, Object> map);

    int deleteExamSubjectByPrimaryKey(Long id);

    int deleteExamSubjectByPrimaryKeys(String ids);

    int insertExamSubject(ExamSubject examSubject);

    int insertExamSubjectSelective(ExamSubject examSubject);

    ExamSubject getExamSubjectByPrimaryKey(Long id);

    List<ExamSubject> listExamSubjects();

    List<ExamSubject> listExamSubjectsByExamId(Long examId);

    List<ExamSubject> listExamSubjectsByPage(Map<String, Object> map);

    int updateExamSubjectByPrimaryKeySelective(ExamSubject examSubject);

    int updateExamSubjectByPrimaryKey(ExamSubject examSubject);
}
