package com.exam.registration.mapper;

import com.exam.registration.model.Admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    long countAdmins();

    int deleteAdminByPrimaryKey(Long id);

    int insertAdmin(Admin record);

    int insertAdminSelective(Admin record);

    List<Admin> listAdmins();

    Admin getAdminByPrimaryKey(Long id);

    Admin getAdminByName(String name);

    int updateAdminByPrimaryKeySelective(Admin record);

    int updateAdminByPrimaryKey(Admin record);
}