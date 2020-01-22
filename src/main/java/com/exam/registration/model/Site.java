package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * site
 *
 */
public class Site implements Serializable {

    /**
     *
     * site.id
     *
     */
    private Long id;

    /**
     * 考点名称
     *
     * site.name
     *
     */
    private String name;

    /**
     * 考点代码
     *
     * site.code
     *
     */
    private String code;

    /**
     * 地址
     *
     * site.address
     *
     */
    private String address;

    /**
     * 允许报考的省份
     *
     * site.allow_province
     *
     */
    private String allowProvince;

    /**
     * 创建时间
     *
     * site.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * site.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * site.is_deleted
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAllowProvince() {
        return allowProvince;
    }

    public void setAllowProvince(String allowProvince) {
        this.allowProvince = allowProvince;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", address=").append(address);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}