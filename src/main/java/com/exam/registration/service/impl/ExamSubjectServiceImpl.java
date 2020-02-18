package com.exam.registration.service.impl;

import com.exam.registration.mapper.ExamSubjectMapper;
import com.exam.registration.model.ExamSubject;
import com.exam.registration.service.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname ExamSubjectServiceImpl
 **/
@Service
public class ExamSubjectServiceImpl implements ExamSubjectService {
    @Autowired
    private ExamSubjectMapper examSubjectMapper;

    @Override
    public long countExamSubjects(Map<String, Object> map) {
        return examSubjectMapper.countExamSubjects(map);
    }

    @Override
    public int deleteExamSubjectByPrimaryKey(Long id) {
        return examSubjectMapper.deleteExamSubjectByPrimaryKey(id);
    }

    @Override
    public int deleteExamSubjectByPrimaryKeys(String ids) {
        return examSubjectMapper.deleteExamSubjectByPrimaryKeys(ids);
    }

    @Override
    public int insertExamSubject(ExamSubject examSubject) {
        Date now = new Date();
        examSubject.setCreateTime(now);
        examSubject.setUpdateTime(now);
        if (Objects.isNull(examSubject.getIsDeleted())) {
            examSubject.setIsDeleted(false);
        }
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
    public List<ExamSubject> listExamSubjectsByPage(Map<String, Object> map) {
        return examSubjectMapper.listExamSubjectsByPage(map);
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
