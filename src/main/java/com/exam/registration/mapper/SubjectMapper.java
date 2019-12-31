package com.exam.registration.mapper;

import com.exam.registration.model.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SubjectMapper {
    long countSubjects(String keyword);

    int deleteSubjectByPrimaryKey(Long id);

    int deleteSubjectByPrimaryKeys(String ids);

    int insertSubject(Subject subject);

    int insertSubjectSelective(Subject subject);

    Subject getSubjectByPrimaryKey(Long id);

    Subject getSubjectByCode(String code);

    List<Subject> listSubjects();

    List<Subject> listSubjectsByPage(Map<String, Object> map);

    List<Subject> listSubjectsByMajorId(long majorId);

    int updateSubjectByPrimaryKeySelective(Subject subject);

    int updateSubjectByPrimaryKey(Subject subject);
}