package com.exam.registration.service.impl;

import com.exam.registration.mapper.StudentMapper;
import com.exam.registration.model.Student;
import com.exam.registration.model.StudentExample;
import com.exam.registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author yhf
 * @classname StudentServiceImpl
 * @description TODO
 * @date 2019/11/27
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public long countByExample(StudentExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Student student) {
        String salt = UUID.randomUUID().toString().substring(0, 5);
        student.setSalt(salt);
        String password = DigestUtils.md5DigestAsHex((student.getPassword() + salt).getBytes());
        student.setPassword(password);
        student.setIsDeleted(false);
        studentMapper.insert(student);
        return 1;
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public List<Student> selectByExample(StudentExample example) {
        return null;
    }

    @Override
    public Student selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Student selectByIdCardNumber(String idCardNumber) {
        return studentMapper.selectByIdCardNumber(idCardNumber);
    }

    @Override
    public int updateByExampleSelective(Student record, StudentExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Student record, StudentExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }

    @Override
    public int login(String idCardNumber, String password) {
        Student queryStudent = selectByIdCardNumber(idCardNumber);
        if (Objects.isNull(queryStudent)) {
            return 0;
        }

        String md5Pass = DigestUtils.md5DigestAsHex((password + queryStudent.getSalt()).getBytes());
        if (md5Pass.equals(password)) {
            return 0;
        }
        return 1;
    }
}
