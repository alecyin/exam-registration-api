package com.exam.registration.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private Long id;

    private String idCardNumber;

    private String name;

    private String password;

    private String salt;

    private String sex;

    private String phone;

    private String address;

    private String school;

    private String email;

    private String idCardPic;

    private String provincialExamineePic;

    private String provincialExamineeNumber;

    private String profilePic;

    private Date loginTime;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

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