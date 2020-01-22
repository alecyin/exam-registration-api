package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试表
 * 
 * exam
 *
 */
public class Exam implements Serializable {
    /**
     *
     * exam.id
     *
     */
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 名称
     *
     * exam.name
     *
     */
    private String name;

    /**
     * 所属专业id
     *
     * exam.major_id
     *
     */
    private Long majorId;

    /**
     * 地点id
     *
     * exam.site_id
     *
     */
    private Long siteId;

    /**
     * 开始考试时间
     *
     * exam.start_time
     *
     */
    private Date startTime;

    /**
     * 结束考试时间
     *
     * exam.end_time
     *
     */
    private Date endTime;

    /**
     * 起始考号
     *
     * exam.start_ examinee_number
     *
     */
    private String startExamineeNumber;

    /**
     * 当前考号
     *
     * exam.current_examinee_number
     *
     */
    private String currentExamineeNumber;

    /**
     * 注意事项
     *
     * exam.note
     *
     */
    private String note;

    /**
     * 创建时间
     *
     * exam.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * exam.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * exam.is_deleted
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

    public String getStartExamineeNumber() {
        return startExamineeNumber;
    }

    public void setStartExamineeNumber(String startExamineeNumber) {
        this.startExamineeNumber = startExamineeNumber;
    }

    public String getCurrentExamineeNumber() {
        return currentExamineeNumber;
    }

    public void setCurrentExamineeNumber(String currentExamineeNumber) {
        this.currentExamineeNumber = currentExamineeNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        sb.append(", majorId=").append(majorId);
        sb.append(", siteId=").append(siteId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", startExamineeNumber=").append(startExamineeNumber);
        sb.append(", currentExamineeNumber=").append(currentExamineeNumber);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}