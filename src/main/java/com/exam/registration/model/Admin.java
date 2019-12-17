package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * admin
 *
 */
public class Admin implements Serializable {
    /**
     *
     * admin.id
     *
     */
    private Long id;

    /**
     * 登录名
     *
     * admin.name
     *
     */
    private String name;

    /**
     * 密码
     *
     * admin.password
     *
     */
    private String password;

    /**
     * 盐
     *
     * admin.salt
     *
     */
    private String salt;

    /**
     * 最后一次登录时间
     *
     * admin.login_time
     *
     */
    private Date loginTime;

    /**
     * 创建时间
     *
     * admin.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * admin.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * admin.is_deleted
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
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
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}