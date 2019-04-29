package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/14
 * createTime:17:22
 * description:
 */

public interface RoleService {
    @LogAnnotation("分页查看角色")
    public Map<String,Object> getAll(Integer page,Integer rows);
    @LogAnnotation("获取所有角色")
    public List<Role> getAllRole();
    @LogAnnotation("添加角色")
    public int addRole(int[] ids, String rolename);
    @LogAnnotation("获取一个角色")
    public int getOneRole(Integer id);
    @LogAnnotation("批量删除多个角色")
    public int deletes(int[] ids);
    @LogAnnotation("更新角色")
    public int update(int[] ids, Role role,String newRoleName);
}
