package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
    @LogAnnotation("分页查询用户")
    public Map<String,Object> getAll(Integer page,Integer rows);
    @LogAnnotation("更新用户")
    public int update(User user);
    @LogAnnotation("添加用户")
    public int addUser(User user);
    @LogAnnotation("删除用户")
    public int deletes(int[] ids);
    @LogAnnotation("导出用户信息")
    public List<User> exportUser(int[] ids);
    @LogAnnotation("导出用户信息")
    public List<User> exportUser();
    @LogAnnotation("导入用户信息")
    public int importUser(List<User> userList);
}
