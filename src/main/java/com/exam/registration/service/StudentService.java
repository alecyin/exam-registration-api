package com.exam.registration.service;

import com.exam.registration.model.Student;
import com.exam.registration.model.StudentExample;

import java.util.List;

public interface StudentService {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Long id);

    Student selectByIdCardNumber(String idCardNumber);

    int updateByExampleSelective(Student record, StudentExample example);

    int updateByExample(Student record, StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
