package com.exam.registration.mapper;

import com.exam.registration.model.ExamineeNote;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ExamineeNoteMapper {
    long countExamineeNotes();

    int deleteExamineeNoteByPrimaryKey(Long id);

    int insertExamineeNote(ExamineeNote examineeNote);

    int insertExamineeNoteSelective(ExamineeNote examineeNote);

    ExamineeNote getExamineeNoteByPrimaryKey(Long id);

    List<ExamineeNote> listExamineeNotes();

    int updateExamineeNoteByPrimaryKeySelective(ExamineeNote examineeNote);

    int updateExamineeNoteByPrimaryKeyWithBLOBs(ExamineeNote examineeNote);

    int updateExamineeNoteByPrimaryKey(ExamineeNote examineeNote);

    int updateExamineeNote(ExamineeNote examineeNote);
}