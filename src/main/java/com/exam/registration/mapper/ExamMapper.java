package com.exam.registration.mapper;

import com.exam.registration.model.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ExamMapper {
    long countExams(Map<String, Object> map);

    int deleteExamByPrimaryKey(Long id);

    int deleteExamByPrimaryKeys(String ids);

    int insertExam(Exam record);

    int insertExamSelective(Exam record);

    Exam getExamByPrimaryKey(Long id);

    List<Exam> listExams();

    List<Exam> listExamsByMajorId(long majorId);

    List<Exam> listExamsBySiteId(long siteId);

    List<Exam> listExamsByPage(Map<String, Object> map);

    int updateExamByPrimaryKeySelective(Exam record);

    int updateExamByPrimaryKey(Exam record);
}