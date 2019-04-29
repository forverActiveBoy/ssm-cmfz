package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Lession;

import java.util.List;

public interface LessionDAO {
    public int insert(Lession lession);
    public int delete(String id);
    public int update(Lession lession);
    public Lession selectOne(String id);
    public List<Lession> selectAllByUser(String cid, int uid);
}
