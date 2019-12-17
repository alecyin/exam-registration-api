package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * announcement
 *
 */
public class Announcement implements Serializable {
    /**
     *
     * announcement.id
     *
     */
    private Long id;

    /**
     * 发布人id
     *
     * announcement.admin_id
     *
     */
    private Long adminId;

    /**
     * 标题
     *
     * announcement.title
     *
     */
    private String title;

    /**
     * 创建时间
     *
     * announcement.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * announcement.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * announcement.is_deleted
     *
     */
    private Boolean isDeleted;

    /**
     * 内容
     *
     * announcement.content
     *
     */
    private String content;

    /**
     */
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adminId=").append(adminId);
        sb.append(", title=").append(title);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}