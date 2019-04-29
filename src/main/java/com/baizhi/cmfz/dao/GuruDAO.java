package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Guru;

import java.util.List;

public interface GuruDAO {
    public int insert(Guru guru);
    public int deletes(String[] ids);
    public int update(Guru guru);
    public List<Guru> selectAll(Integer start,Integer rows);
    public int selectCount();
    public List<Guru> selectAllToAddAriticle();
    public Guru selectOne(String id);
}
