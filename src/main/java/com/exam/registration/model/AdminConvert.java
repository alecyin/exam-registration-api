package com.exam.registration.model;

/**
 * @author yhf
 * @classname AdminConvert
 * @description TODO
 * @date 2019/12/18
 **/
public class AdminConvert {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getAdmin() {
        Admin admin = new Admin();
        admin.setName(this.username);
        admin.setPassword(this.password);
        return admin;
    }
}
