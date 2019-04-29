package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.Role;

import java.util.List;

public interface AdminDAO {
    public int insert(Admin admin);
    public int insertAdminRole(String username,int roleid);
    public int deletes(String[] ids);
    public int deleteAdminRole(String usernmae);
    public int update(Admin admin);
    public Admin selectOneById(String id);
    public Admin selectOneByUsername(String username);
    public List<Role> selectAllRoleByUsername(String username);
    public int selectCount();
    public List<Admin> selectAll(Integer start, Integer rows);

}
