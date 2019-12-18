package com.exam.registration.service.impl;

import com.exam.registration.mapper.SubjectMapper;
import com.exam.registration.model.Subject;
import com.exam.registration.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yhf
 * @classname SubjectServiceImpl
 * @description TODO
 * @date 2019/12/18
 **/
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public long countSubjects() {
        return 0;
    }

    @Override
    public int deleteSubjectByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSubject(Subject subject) {
        Date now = new Date();
        subject.setCreateTime(now);
        subject.setUpdateTime(now);
        subject.setIsDeleted(false);
        return subjectMapper.insertSubject(subject);
    }

    @Override
    public int insertSubjectSelective(Subject subject) {
        return 0;
    }

    @Override
    public Subject getSubjectByPrimaryKey(Long id) {
        return subjectMapper.getSubjectByPrimaryKey(id);
    }

    @Override
    public Subject getSubjectByCode(String code) {
        return subjectMapper.getSubjectByCode(code);
    }

    @Override
    public List<Subject> listSubjects() {
        return subjectMapper.listSubjects();
    }

    @Override
    public int updateSubjectByPrimaryKeySelective(Subject subject) {
        subject.setUpdateTime(new Date());
        return subjectMapper.updateSubjectByPrimaryKeySelective(subject);
    }

    @Override
    public int updateSubjectByPrimaryKey(Subject subject) {
        return 0;
    }
}
