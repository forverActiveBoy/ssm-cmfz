package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ListenDAO;
import com.baizhi.cmfz.entity.Listen;
import com.baizhi.cmfz.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/7
 * createTime:14:56
 * description:
 */
@Service
public class ListenServiceImpl implements ListenService {
    @Autowired
    private ListenDAO ld;
    @Override
    public int addListen(Listen listen) {
        return ld.insert(listen);
    }

    @Override
    public int deletes(String[] ids) {
        return ld.deletes(ids);
    }

    @Override
    public int update(Listen listen) {
        return ld.update(listen);
    }

    @Override
    public Map<String, Object> getAll(Integer page,Integer rows) {
        Integer start = (page -1) * rows;
        List<Listen> listenList = ld.selectAll(start, rows);
        int count = ld.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",listenList);
        return map;
    }

    @Override
    public List<Listen> getAllAlbum() {
        List<Listen> listenList = ld.selectAllListen();
        return listenList;
    }

    @Override
    public Listen getOne(String id) {
        return ld.selectOne(id);
    }
}
