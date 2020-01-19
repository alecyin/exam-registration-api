package com.exam.registration.service.impl;

import com.exam.registration.mapper.StudentMapper;
import com.exam.registration.model.Student;
import com.exam.registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public long countStudents(String keyword) {
        return studentMapper.countStudents(keyword);
    }

    @Override
    @Transactional
    public int deleteStudentByPrimaryKey(Long id) {
        return studentMapper.deleteStudentByPrimaryKey(id);
    }

    @Override
    public int deleteStudentByPrimaryKeys(String ids) {
        return studentMapper.deleteStudentByPrimaryKeys(ids);
    }

    @Override
    public int deleteStudentByIdCardNumber(String idCardNumber) {
        return studentMapper.deleteStudentByIdCardNumber(idCardNumber);
    }

    @Override
    public int insertStudent(Student student) {
        String salt = UUID.randomUUID().toString().substring(0, 5);
        student.setSalt(salt);
        String password = DigestUtils.md5DigestAsHex((student.getPassword() + salt).getBytes());
        student.setPassword(password);
        student.setIsDeleted(false);
        Date now = new Date();
        student.setCreateTime(now);
        student.setUpdateTime(now);
        return studentMapper.insertStudent(student);
    }

    @Override
    public int insertStudentSelective(Student record) {
        return 0;
    }

    @Override
    public List<Student> listStudents() {
        return studentMapper.listStudents();
    }

    @Override
    public List<Student> listStudentsByPage(Map<String, Object> map) {
        return studentMapper.listStudentsByPage(map);
    }

    @Override
    public Student getStudentByPrimaryKey(Long id) {
        return studentMapper.getStudentByPrimaryKey(id);
    }

    @Override
    public Student getStudentByIdCardNumber(String idCardNumber) {
        return studentMapper.getStudentByIdCardNumber(idCardNumber);
    }

    @Override
    public int updateStudentByPrimaryKeySelective(Student student) {
        Student queryStudent = getStudentByPrimaryKey(student.getId());
        if (Objects.isNull(queryStudent)) {
            return 0;
        }

        if (!StringUtils.isEmpty(student.getPassword()) &&
                !student.getPassword().equals(queryStudent.getPassword())) {
            String newPass = DigestUtils.md5DigestAsHex((student.getPassword() + queryStudent.getSalt())
                    .getBytes());
            student.setPassword(newPass);
        }

        student.setUpdateTime(new Date());

        return studentMapper.updateStudentByPrimaryKeySelective(student);
    }

    @Override
    public int updateStudentByPrimaryKey(Student student) {
        return 0;
    }

    @Override
    public int login(Student student) {
        Student queryStudent = getStudentByIdCardNumber(student.getIdCardNumber());
        if (Objects.isNull(queryStudent)) {
            return 0;
        }

        String md5Pass = DigestUtils.md5DigestAsHex((student.getPassword() + queryStudent.getSalt()).getBytes());
        if (md5Pass.equals(queryStudent.getPassword())) {
            return 1;
        }
        return 0;
    }
}
