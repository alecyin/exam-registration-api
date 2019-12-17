package com.exam.registration.service;

import com.exam.registration.model.ExamineeNote;

import java.util.List;

/**
 * @author yhf
 * @classname ExamineeNoteService
 * @description TODO
 * @date 2019/12/17
 **/
public interface ExamineeNoteService {
    int insertExamineeNote(ExamineeNote examineeNote);

    int updateExamineeNote(ExamineeNote examineeNote);

    ExamineeNote getExamineeNote();

    boolean isExisted();
}
