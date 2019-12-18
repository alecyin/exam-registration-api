package com.exam.registration.service;

import com.exam.registration.model.Exam;

import java.util.List;

public interface ExamService {
    long countExams();

    int deleteExamByPrimaryKey(Long id);

    int insertExam(Exam record);

    int insertExamSelective(Exam record);

    Exam getExamByPrimaryKey(Long id);

    List<Exam> listExams();

    int updateExamByPrimaryKeySelective(Exam record);

    int updateExamByPrimaryKey(Exam record);
}
