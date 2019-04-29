package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Listen;

import java.util.List;
import java.util.Map;

public interface ListenService {
    @LogAnnotation("添加音频专辑")
    public int addListen(Listen listen);
    @LogAnnotation("删除音频专辑")
    public int deletes(String[] id);
    @LogAnnotation("更新音频专辑")
    public int update(Listen listen);
    @LogAnnotation("分页查询音频专辑")
    public Map<String,Object> getAll(Integer page,Integer rows);
    @LogAnnotation("查询所有音频专辑")
    public List<Listen> getAllAlbum();
    @LogAnnotation("查询一个音频专辑")
    public Listen getOne(String id);
}
