package com.exam.registration.service.impl;

import com.exam.registration.model.Admin;
import com.exam.registration.model.AdminExample;
import com.exam.registration.service.AdminService;

import java.util.List;

/**
 * @author yhf
 * @classname AdminServiceImpl
 * @description TODO
 * @date 2019/11/27
 **/
public class AdminServiceImpl implements AdminService {
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
}
