package com.exam.registration.service;

import com.exam.registration.model.Student;

import java.util.List;

public interface StudentService {

    long countStudents();

    int deleteStudentByPrimaryKey(Long id);

    int deleteStudentByIdCardNumber(String idCardNumber);

    int insertStudent(Student record);

    int insertStudentSelective(Student record);

    List<Student> listStudents();

    Student getStudentByPrimaryKey(Long id);

    Student getStudentByIdCardNumber(String idCardNumber);

    int updateStudentByPrimaryKeySelective(Student record);

    int updateStudentByPrimaryKey(Student record);

    int login(String idCardNumber, String password);
}
