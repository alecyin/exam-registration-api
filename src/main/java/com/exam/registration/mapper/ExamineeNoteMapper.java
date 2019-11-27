package com.exam.registration.mapper;

import com.exam.registration.model.ExamineeNote;
import com.exam.registration.model.ExamineeNoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineeNoteMapper {
    long countByExample(ExamineeNoteExample example);

    int deleteByExample(ExamineeNoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamineeNote record);

    int insertSelective(ExamineeNote record);

    List<ExamineeNote> selectByExampleWithBLOBs(ExamineeNoteExample example);

    List<ExamineeNote> selectByExample(ExamineeNoteExample example);

    ExamineeNote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamineeNote record, @Param("example") ExamineeNoteExample example);

    int updateByExampleWithBLOBs(@Param("record") ExamineeNote record, @Param("example") ExamineeNoteExample example);

    int updateByExample(@Param("record") ExamineeNote record, @Param("example") ExamineeNoteExample example);

    int updateByPrimaryKeySelective(ExamineeNote record);

    int updateByPrimaryKeyWithBLOBs(ExamineeNote record);
}