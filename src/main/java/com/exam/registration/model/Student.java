package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 * 
 * student
 *
 */
public class Student implements Serializable {
    /**
     *
     * student.id
     *
     */
    private Long id;

    /**
     * 身份证号码
     *
     * student.id_card_number
     *
     */
    private String idCardNumber;

    /**
     * 姓名
     *
     * student.name
     *
     */
    private String name;

    /**
     * 密码
     *
     * student.password
     *
     */
    private String password;

    /**
     * 盐
     *
     * student.salt
     *
     */
    private String salt;

    /**
     * 性别
     *
     * student.sex
     *
     */
    private String sex;

    /**
     * 手机号
     *
     * student.phone
     *
     */
    private String phone;

    /**
     * 地址
     *
     * student.address
     *
     */
    private String address;

    /**
     * 学校
     *
     * student.school
     *
     */
    private String school;

    /**
     * 邮箱
     *
     * student.email
     *
     */
    private String email;

    /**
     * 身份证正面照片
     *
     * student.id_card_pic
     *
     */
    private String idCardPic;

    /**
     * 省准考证照片
     *
     * student.provincial_examinee_pic
     *
     */
    private String provincialExamineePic;

    /**
     * 省准考证号码
     *
     * student.provincial_examinee_number
     *
     */
    private String provincialExamineeNumber;

    /**
     * 本人照片
     *
     * student.profile_pic
     *
     */
    private String profilePic;

    /**
     * 最后一次登录时间
     *
     * student.login_time
     *
     */
    private Date loginTime;

    /**
     * 创建时间
     *
     * student.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * student.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * student.is_deleted
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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardPic() {
        return idCardPic;
    }

    public void setIdCardPic(String idCardPic) {
        this.idCardPic = idCardPic;
    }

    public String getProvincialExamineePic() {
        return provincialExamineePic;
    }

    public void setProvincialExamineePic(String provincialExamineePic) {
        this.provincialExamineePic = provincialExamineePic;
    }

    public String getProvincialExamineeNumber() {
        return provincialExamineeNumber;
    }

    public void setProvincialExamineeNumber(String provincialExamineeNumber) {
        this.provincialExamineeNumber = provincialExamineeNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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
        sb.append(", idCardNumber=").append(idCardNumber);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", school=").append(school);
        sb.append(", email=").append(email);
        sb.append(", idCardPic=").append(idCardPic);
        sb.append(", provincialExamineePic=").append(provincialExamineePic);
        sb.append(", provincialExamineeNumber=").append(provincialExamineeNumber);
        sb.append(", profilePic=").append(profilePic);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}