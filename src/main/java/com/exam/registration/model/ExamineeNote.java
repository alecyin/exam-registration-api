package com.exam.registration.model;

import java.io.Serializable;

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
     * 启用状态
     *
     * examinee_note.is_deleted
     *
     */
    private Boolean isDeleted;

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
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}