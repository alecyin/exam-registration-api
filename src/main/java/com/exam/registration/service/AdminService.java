package com.exam.registration.service;

import com.exam.registration.model.Admin;

import java.util.List;

public interface AdminService {
    long countAdmins();

    int deleteAdminByPrimaryKey(Long id);

    int insertAdmin(Admin record);

    int insertAdminSelective(Admin record);

    List<Admin> listAdmins();

    Admin getAdminByPrimaryKey(Long id);

    Admin getAdminByName(String name);

    int updateAdminByPrimaryKeySelective(Admin record);

    int updateAdminByPrimaryKey(Admin record);

    int login(Admin admin);
}
