package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * examinee_note
 *
 */
public class ExamineeNote implements Serializable {
    /**
     *
     * examinee_note.id
     *
     */
    private Long id;

    /**
     * 创建时间
     *
     * examinee_note.create_time
     *
     */
    private Date createTime;

    /**
     * 修改时间
     *
     * examinee_note.update_time
     *
     */
    private Date updateTime;

    /**
     * 准考证注意事项
     *
     * examinee_note.content
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
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}