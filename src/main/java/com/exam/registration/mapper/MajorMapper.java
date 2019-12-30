package com.exam.registration.mapper;

import com.exam.registration.model.Major;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MajorMapper {
    long countMajors(String keyword);

    int deleteMajorByPrimaryKey(Long id);

    int deleteMajorByPrimaryKeys(String ids);

    int insertMajor(Major record);

    int insertMajorSelective(Major record);

    Major getMajorByPrimaryKey(Long id);

    List<Major> listMajors();

    List<Major> listMajorsByPage(Map<String, Object> map);

    int updateMajorByPrimaryKeySelective(Major record);

    int updateMajorByPrimaryKey(Major record);
}