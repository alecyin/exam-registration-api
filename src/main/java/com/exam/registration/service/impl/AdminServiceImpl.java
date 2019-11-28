package com.exam.registration.service.impl;

import com.exam.registration.mapper.AdminMapper;
import com.exam.registration.model.Admin;
import com.exam.registration.model.AdminExample;
import com.exam.registration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author yhf
 * @classname AdminServiceImpl
 * @description TODO
 * @date 2019/11/27
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public long countByExample(AdminExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AdminExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Admin record) {
        return 0;
    }

    @Override
    public int insertSelective(Admin record) {
        return 0;
    }

    @Override
    public List<Admin> selectByExample(AdminExample example) {
        return null;
    }

    @Override
    public Admin selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Admin selectByName(String name) {
        return adminMapper.selectByName(name);
    }

    @Override
    public int updateByExampleSelective(Admin record, AdminExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Admin record, AdminExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return 0;
    }

    @Override
    public int login(String name, String password) {
        Admin queryAdmin = selectByName(name);
        if (Objects.isNull(queryAdmin)) {
            return 0;
        }

        String md5Pass = DigestUtils.md5DigestAsHex((password + queryAdmin.getSalt()).getBytes());
        if (md5Pass.equals(password)) {
            return 0;
        }
        return 1;
    }
}
