package com.exam.registration.mapper;

import com.exam.registration.model.ExamSubject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ExamSubjectMapper {
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