package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.CMFZLog;

import java.util.List;

public interface CMFZLogDAO {
    public List<CMFZLog> selectAll(Integer start,Integer rows);
    public int deletes(int[] ids);
    public int selectCount();
    public int insert(CMFZLog cmfzLog);
    public List<CMFZLog> selectAllCMFZlog();
}
