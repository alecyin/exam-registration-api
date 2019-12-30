package com.exam.registration.service;

import com.exam.registration.model.Exam;

import java.util.List;
import java.util.Map;

public interface ExamService {
    long countExams(Map<String, Object> map);

    int deleteExamByPrimaryKey(Long id);

    int deleteExamByPrimaryKeys(String ids);

    int insertExam(Exam record);

    int insertExamSelective(Exam record);

    Exam getExamByPrimaryKey(Long id);

    List<Exam> listExams();

    List<Exam> listExamsByPage(Map<String, Object> map);

    int updateExamByPrimaryKeySelective(Exam record);

    int updateExamByPrimaryKey(Exam record);
}
