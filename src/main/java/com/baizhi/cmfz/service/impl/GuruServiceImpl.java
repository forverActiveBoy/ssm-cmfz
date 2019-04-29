package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.GuruDAO;
import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/6
 * createTime:15:29
 * description:
 */
@Service
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDAO gd;
    @Override
    public int addGuru(Guru guru) {
        return gd.insert(guru);
    }

    @Override
    public int deletes(String[] ids) {
        return gd.deletes(ids);
    }

    @Override
    public Guru getOne(String id) {
        return gd.selectOne(id);
    }

    @Override
    public int update(Guru guru) {
        return gd.update(guru);
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer rows) {

        Integer start = (page - 1) * rows;
        List<Guru> guruList = gd.selectAll(start, rows);
        int count = gd.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",guruList);
        return map;
    }

    @Override
    public List<Guru> getAllToAddAriticle() {
        return gd.selectAllToAddAriticle();
    }
}
