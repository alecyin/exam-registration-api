package com.exam.registration.service;

import com.exam.registration.model.Subject;
import com.exam.registration.model.SubjectExample;

import java.util.List;

public interface SubjectService {
    long countByExample(SubjectExample example);

    int deleteByExample(SubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Long id);

    int updateByExampleSelective(Subject record, SubjectExample example);

    int updateByExample(Subject record, SubjectExample example);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
}
