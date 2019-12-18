package com.exam.registration.service.impl;

import com.exam.registration.mapper.ExamSubjectMapper;
import com.exam.registration.model.ExamSubject;
import com.exam.registration.service.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yhf
 * @classname ExamSubjectServiceImpl
 * @description TODO
 * @date 2019/12/18
 **/
@Service
public class ExamSubjectServiceImpl implements ExamSubjectService {
    @Autowired
    private ExamSubjectMapper examSubjectMapper;

    @Override
    public long countExamSubjects() {
        return examSubjectMapper.countExamSubjects();
    }

    @Override
    public int deleteExamSubjectByPrimaryKey(Long id) {
        return examSubjectMapper.deleteExamSubjectByPrimaryKey(id);
    }

    @Override
    public int insertExamSubject(ExamSubject examSubject) {
        Date now = new Date();
        examSubject.setCreateTime(now);
        examSubject.setUpdateTime(now);
        examSubject.setIsDeleted(false);
        return examSubjectMapper.insertExamSubject(examSubject);
    }

    @Override
    public int insertExamSubjectSelective(ExamSubject examSubject) {
        return 0;
    }

    @Override
    public ExamSubject getExamSubjectByPrimaryKey(Long id) {
        return examSubjectMapper.getExamSubjectByPrimaryKey(id);
    }

    @Override
    public List<ExamSubject> listExamSubjects() {
        return examSubjectMapper.listExamSubjects();
    }

    @Override
    public int updateExamSubjectByPrimaryKeySelective(ExamSubject examSubject) {
        examSubject.setUpdateTime(new Date());
        return examSubjectMapper.updateExamSubjectByPrimaryKeySelective(examSubject);
    }

    @Override
    public int updateExamSubjectByPrimaryKey(ExamSubject examSubject) {
        return 0;
    }
}
