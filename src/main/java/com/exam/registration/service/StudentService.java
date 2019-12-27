package com.exam.registration.service;

import com.exam.registration.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    long countStudents();

    int deleteStudentByPrimaryKey(Long id);

    int deleteStudentByIdCardNumber(String idCardNumber);

    int insertStudent(Student record);

    int insertStudentSelective(Student record);

    List<Student> listStudents();

    List<Student> listStudentsByPage(Map<String, Object> map);

    Student getStudentByPrimaryKey(Long id);

    Student getStudentByIdCardNumber(String idCardNumber);

    int updateStudentByPrimaryKeySelective(Student record);

    int updateStudentByPrimaryKey(Student record);

    int login(Student student);
}
