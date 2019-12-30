package com.exam.registration.mapper;

import com.exam.registration.model.Admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
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
}