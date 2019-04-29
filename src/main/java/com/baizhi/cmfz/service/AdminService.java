package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Admin;

import java.util.Map;

public interface AdminService {
    @LogAnnotation("添加管理员")
    public int insert(Admin admin,int[] ids);
    @LogAnnotation("删除管理员")
    public int deletes(String[] ids);
    @LogAnnotation("更新管理员")
    public int update(Admin admin,int[] ids);
    @LogAnnotation("根据Id查询管理员")
    public Admin getOneById(String id);
    @LogAnnotation("管理员登录")
    public boolean login(String username,String password);
    @LogAnnotation("分页查询管理员")
    public Map<String,Object> getAll(Integer page,Integer rows);
}
