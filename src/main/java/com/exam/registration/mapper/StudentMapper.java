package com.exam.registration.mapper;

import com.exam.registration.model.Student;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    long countStudents(String keyword);

    int deleteStudentByPrimaryKey(Long id);

    int deleteStudentByPrimaryKeys(String ids);

    int deleteStudentByIdCardNumber(String idCardNumber);

    int insertStudent(Student record);

    int insertStudentSelective(Student record);

    List<Student> listStudents();

    List<Student> listStudentsByPage(Map<String, Object> map);

    Student getStudentByPrimaryKey(Long id);

    Student getStudentByIdCardNumber(String idCardNumber);

    int updateStudentByPrimaryKeySelective(Student record);

    int updateStudentByPrimaryKey(Student record);
}