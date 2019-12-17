package com.exam.registration.mapper;

import com.exam.registration.model.ExamineeNote;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExamineeNoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExamineeNote record);

    int insertSelective(ExamineeNote record);

    ExamineeNote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExamineeNote record);

    int updateByPrimaryKeyWithBLOBs(ExamineeNote record);

    int updateByPrimaryKey(ExamineeNote record);
}