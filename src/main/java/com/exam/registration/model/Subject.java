package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * subject
 *
 */
public class Subject implements Serializable {
    /**
     *
     * subject.id
     *
     */
    private Long id;

    /**
     * 所属专业id
     *
     * subject.major_id
     *
     */
    private Long majorId;

    /**
     * 科目名称
     *
     * subject.name
     *
     */
    private String name;

    /**
     * 科目代码
     *
     * subject.code
     *
     */
    private String code;

    /**
     * 1=面试；2=笔试；
     *
     * subject.type
     *
     */
    private Boolean type;

    /**
     * 创建时间
     *
     * subject.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * subject.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * subject.is_deleted
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}