package com.exam.registration.service;

import com.exam.registration.model.Subject;

import java.util.List;

public interface SubjectService {
    long countSubjects();

    int deleteSubjectByPrimaryKey(Long id);

    int insertSubject(Subject subject);

    int insertSubjectSelective(Subject subject);

    Subject getSubjectByPrimaryKey(Long id);

    Subject getSubjectByCode(String code);

    List<Subject> listSubjects();

    int updateSubjectByPrimaryKeySelective(Subject subject);

    int updateSubjectByPrimaryKey(Subject subject);
}
