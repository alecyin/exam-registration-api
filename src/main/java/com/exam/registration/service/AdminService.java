package com.exam.registration.service;

import com.exam.registration.model.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    long countAdmins(String keyword);

    int deleteAdminByPrimaryKey(Long id);

    int deleteAdminByPrimaryKeys(String ids);

    int insertAdmin(Admin record);

    int insertAdminSelective(Admin record);

    List<Admin> listAdmins();

    List<Admin> listAdminsByPage(Map<String, Object> map);

    Admin getAdminByPrimaryKey(Long id);

    Admin getAdminByName(String name);

    int updateAdminByPrimaryKeySelective(Admin record);

    int updateAdminByPrimaryKey(Admin record);

    int login(Admin admin);
}
