package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.dao.AdminDAO;
import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author:bobo大人
 * createDate:2018/8/13
 * createTime:20:26
 * description:
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Override
    public int insert(Admin admin,int[] ids) {
        System.out.println(admin);
        String beforepassword = admin.getBeforepassword();
        String salt = UUID.randomUUID().toString();
        admin.setPasswordsalt(salt);
        Md5Hash md5Hash = new Md5Hash(beforepassword,salt,1024);
        String password = md5Hash.toString();
        admin.setPassword(password);
        adminDAO.insert(admin);

        for (int id : ids){
            adminDAO.insertAdminRole(admin.getUsername(),id);
        }
        return 1;
    }

    @Override
    public int deletes(String[] ids) {
        return adminDAO.deletes(ids);
    }

    @Override
    public int update(Admin admin,int[] ids) {

        String passwordsalt = admin.getPasswordsalt();
        String beforepassword = admin.getBeforepassword();
        Md5Hash md5Hash = new Md5Hash(beforepassword,passwordsalt,1024);
        String password = md5Hash.toString();

        admin.setPassword(password);

        int i = adminDAO.deleteAdminRole(admin.getUsername());

        adminDAO.update(admin);

        for (int id : ids){
            adminDAO.insertAdminRole(admin.getUsername(),id);
        }

        return i;
    }

    @LogAnnotation("管理员登录")
    @Override
    public Admin getOneById(String id) {
        return adminDAO.selectOneById(id);
    }

    @Override
    public boolean login(String username, String password) {

        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();

            subject.login(new UsernamePasswordToken(username,password));

            String uname = subject.getPrincipal().toString();
            System.out.println(uname);
        } catch (Exception e) {
            e.printStackTrace();
            Object uname = subject.getPrincipal();
            System.out.println(uname);
            return false;
        }

        return true;
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Admin> adminList = adminDAO.selectAll(start, rows);
        int count = adminDAO.selectCount();
        for(Admin admin : adminList){
            List<Role> roles = adminDAO.selectAllRoleByUsername(admin.getUsername());
            admin.setRoleList(roles);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",adminList);
        return map;
    }
}
