package com.exam.registration.service.impl;

import com.exam.registration.mapper.ExamineeNoteMapper;
import com.exam.registration.model.ExamineeNote;
import com.exam.registration.service.ExamineeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yhf
 * @classname ExamineeNoteServiceImpl
 * @description TODO
 * @date 2019/12/17
 **/
@Service
public class ExamineeNoteServiceImpl implements ExamineeNoteService {
    @Autowired
    private ExamineeNoteMapper examineeNoteMapper;

    @Override
    public int insertExamineeNote(ExamineeNote examineeNote) {
        Date now = new Date();
        examineeNote.setCreateTime(now);
        examineeNote.setUpdateTime(now);
        return examineeNoteMapper.insertExamineeNote(examineeNote);
    }

    @Override
    public int updateExamineeNote(ExamineeNote examineeNote) {
        examineeNote.setUpdateTime(new Date());
        return examineeNoteMapper.updateExamineeNote(examineeNote);
    }

    @Override
    public ExamineeNote getExamineeNote() {
        return examineeNoteMapper.listExamineeNotes().get(0);
    }

    @Override
    public boolean isExisted() {
        return examineeNoteMapper.countExamineeNotes() > 0;
    }
}
