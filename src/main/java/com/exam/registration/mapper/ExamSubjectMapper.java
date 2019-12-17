package com.exam.registration.mapper;

import com.exam.registration.model.ExamSubject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExamSubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExamSubject record);

    int insertSelective(ExamSubject record);

    ExamSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExamSubject record);

    int updateByPrimaryKey(ExamSubject record);
}