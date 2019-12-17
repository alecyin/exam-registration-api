package com.exam.registration.mapper;

import com.exam.registration.model.Major;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface MajorMapper {
    long countMajors();

    int deleteMajorByPrimaryKey(Long id);

    int insertMajor(Major record);

    int insertMajorSelective(Major record);

    Major getMajorByPrimaryKey(Long id);

    List<Major> listMajors();

    int updateMajorByPrimaryKeySelective(Major record);

    int updateMajorByPrimaryKey(Major record);
}