package com.exam.registration.service.impl;

import com.exam.registration.mapper.AdminMapper;
import com.exam.registration.model.Admin;
import com.exam.registration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.DateUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    public long countAdmins() {
        return adminMapper.countAdmins();
    }

    @Override
    public int deleteAdminByPrimaryKey(Long id) {
        return adminMapper.deleteAdminByPrimaryKey(id);
    }

    @Override
    public int insertAdmin(Admin admin) {
        String salt = UUID.randomUUID().toString().substring(0, 5);
        admin.setSalt(salt);
        String password = DigestUtils.md5DigestAsHex((admin.getPassword() + salt).getBytes());
        admin.setPassword(password);
        admin.setIsDeleted(false);
        Date now = new Date();
        admin.setCreateTime(now);
        admin.setUpdateTime(now);
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public int insertAdminSelective(Admin record) {
        return 0;
    }

    @Override
    public List<Admin> listAdmins() {
        return adminMapper.listAdmins();
    }

    @Override
    public Admin getAdminByPrimaryKey(Long id) {
        return adminMapper.getAdminByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByName(String name) {
        return adminMapper.getAdminByName(name);
    }

    @Override
    public int updateAdminByPrimaryKeySelective(Admin admin) {
        Admin queryAdmin = getAdminByPrimaryKey(admin.getId());
        if (Objects.isNull(queryAdmin)) {
            return 0;
        }

        if (!StringUtils.isEmpty(queryAdmin.getPassword())) {
            String newPass = DigestUtils.md5DigestAsHex((admin.getPassword() + queryAdmin.getSalt())
                    .getBytes());
            admin.setPassword(newPass);
        }

        admin.setUpdateTime(new Date());

        return adminMapper.updateAdminByPrimaryKeySelective(admin);
    }

    @Override
    public int updateAdminByPrimaryKey(Admin record) {
        return 0;
    }

    @Override
    public int login(String name, String password) {
        Admin queryAdmin = getAdminByName(name);
        if (Objects.isNull(queryAdmin)) {
            return 0;
        }

        String md5Pass = DigestUtils.md5DigestAsHex((password + queryAdmin.getSalt()).getBytes());
        if (md5Pass.equals(queryAdmin.getPassword())) {
            return 1;
        }
        return 0;
    }
}
