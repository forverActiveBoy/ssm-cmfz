package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Listen;

import java.util.List;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:16:50
 * description:专辑DAO
 */
public interface ListenDAO {
    public List<Listen> selectAll(Integer start,Integer rows);
    public List<Listen> selectAllListen();
    public int insert(Listen listen);
    public int deletes(String[] ids);
    public int update(Listen listen);
    public int selectCount();
    public Listen selectOne(String id);
}
