package com.exam.registration.service;

import com.exam.registration.model.ExamineeNote;
import com.exam.registration.model.ExamineeNoteExample;

import java.util.List;

/**
 * @author yhf
 * @classname ExamineeNoteService
 * @description TODO
 * @date 2019/11/27
 **/
public interface ExamineeNoteService {
    long countByExample(ExamineeNoteExample example);

    int deleteByExample(ExamineeNoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamineeNote record);

    int insertSelective(ExamineeNote record);

    List<ExamineeNote> selectByExampleWithBLOBs(ExamineeNoteExample example);

    List<ExamineeNote> selectByExample(ExamineeNoteExample example);

    ExamineeNote selectByPrimaryKey(Long id);

    int updateByExampleSelective(ExamineeNote record, ExamineeNoteExample example);

    int updateByExampleWithBLOBs(ExamineeNote record, ExamineeNoteExample example);

    int updateByExample(ExamineeNote record, ExamineeNoteExample example);

    int updateByPrimaryKeySelective(ExamineeNote record);

    int updateByPrimaryKeyWithBLOBs(ExamineeNote record);
}
