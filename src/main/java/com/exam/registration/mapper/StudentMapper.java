package com.exam.registration.mapper;

import com.exam.registration.model.Student;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
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
}