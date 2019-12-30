package com.exam.registration.service;

import com.exam.registration.model.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    long countSubjects(String keyword);

    int deleteSubjectByPrimaryKey(Long id);

    int deleteSubjectByPrimaryKeys(String ids);

    int insertSubject(Subject subject);

    int insertSubjectSelective(Subject subject);

    Subject getSubjectByPrimaryKey(Long id);

    Subject getSubjectByCode(String code);

    List<Subject> listSubjects();

    List<Subject> listSubjectsByPage(Map<String, Object> map);

    int updateSubjectByPrimaryKeySelective(Subject subject);

    int updateSubjectByPrimaryKey(Subject subject);
}
