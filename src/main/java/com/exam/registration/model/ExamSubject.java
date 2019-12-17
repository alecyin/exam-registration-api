package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * exam_subject
 *
 */
public class ExamSubject implements Serializable {
    /**
     *
     * exam_subject.id
     *
     */
    private Long id;

    /**
     * 开始时间
     *
     * exam_subject.start_time
     *
     */
    private Date startTime;

    /**
     * 结束时间
     *
     * exam_subject.end_time
     *
     */
    private Date endTime;

    /**
     * 地点
     *
     * exam_subject.address
     *
     */
    private String address;

    /**
     *
     * exam_subject.subject_id
     *
     */
    private Long subjectId;

    /**
     *
     * exam_subject.exam_id
     *
     */
    private Long examId;

    /**
     *
     * exam_subject.create_time
     *
     */
    private Date createTime;

    /**
     *
     * exam_subject.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * exam_subject.is_deleted
     *
     */
    private Boolean isDeleted;

    /**
     */
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", address=").append(address);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", examId=").append(examId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}