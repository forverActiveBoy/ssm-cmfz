package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    @LogAnnotation("添加上师")
    public int addGuru(Guru guru);
    @LogAnnotation("删除上师")
    public int deletes(String[] ids);
    @LogAnnotation("查询一个上师")
    public Guru getOne(String id);
    @LogAnnotation("更新上师")
    public int update(Guru guru);
    @LogAnnotation("分页查询上师")
    public Map<String,Object> getAll(Integer page,Integer rows);
    @LogAnnotation("查询所有上师用于添加文章")
    public List<Guru> getAllToAddAriticle();
}
