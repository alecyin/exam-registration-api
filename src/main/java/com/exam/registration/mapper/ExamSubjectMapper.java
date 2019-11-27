package com.exam.registration.mapper;

import com.exam.registration.model.ExamSubject;
import com.exam.registration.model.ExamSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamSubjectMapper {
    long countByExample(ExamSubjectExample example);

    int deleteByExample(ExamSubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamSubject record);

    int insertSelective(ExamSubject record);

    List<ExamSubject> selectByExample(ExamSubjectExample example);

    ExamSubject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamSubject record, @Param("example") ExamSubjectExample example);

    int updateByExample(@Param("record") ExamSubject record, @Param("example") ExamSubjectExample example);

    int updateByPrimaryKeySelective(ExamSubject record);

    int updateByPrimaryKey(ExamSubject record);
}