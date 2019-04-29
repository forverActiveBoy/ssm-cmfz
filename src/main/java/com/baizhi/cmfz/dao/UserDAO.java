package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.User;

import java.util.List;

public interface UserDAO {
    public int insert(User user);
    public int importInsert(User user);
    public int update(User user);
    public int deletes(int[] ids);
    public User selectOneByTelphoneAndPassword(String telphone, String password);
    public List<User> selectFive(int id);
    public List<User> selectAll(Integer start,Integer rows);
    public List<User> selectAllUser();
    public List<User> selectAllById(int[] ids);
    public int selectCount();
}