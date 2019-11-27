package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    private Long id;

    private Long majorId;

    private Long siteId;

    private Date startTime;

    private Date endTime;

    private String conflict;

    private String province;

    private String startExamineeNumber;

    private String currentExamineeNumber;

    private String note;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getConflict() {
        return conflict;
    }

    public void setConflict(String conflict) {
        this.conflict = conflict == null ? null : conflict.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getStartExamineeNumber() {
        return startExamineeNumber;
    }

    public void setStartExamineeNumber(String startExamineeNumber) {
        this.startExamineeNumber = startExamineeNumber == null ? null : startExamineeNumber.trim();
    }

    public String getCurrentExamineeNumber() {
        return currentExamineeNumber;
    }

    public void setCurrentExamineeNumber(String currentExamineeNumber) {
        this.currentExamineeNumber = currentExamineeNumber == null ? null : currentExamineeNumber.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", majorId=").append(majorId);
        sb.append(", siteId=").append(siteId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", conflict=").append(conflict);
        sb.append(", province=").append(province);
        sb.append(", startExamineeNumber=").append(startExamineeNumber);
        sb.append(", currentExamineeNumber=").append(currentExamineeNumber);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", majorId=").append(majorId);
        sb.append(", siteId=").append(siteId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", conflict=").append(conflict);
        sb.append(", province=").append(province);
        sb.append(", startExamineeNumber=").append(startExamineeNumber);
        sb.append(", currentExamineeNumber=").append(currentExamineeNumber);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}