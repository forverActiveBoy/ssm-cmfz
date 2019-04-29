package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Audio;

import java.util.List;
import java.util.Map;

public interface AudioService {
    @LogAnnotation("添加音频")
    public int addAudio(Audio audio);
    @LogAnnotation("删除音频")
    public int deletes(String[] ids);
    @LogAnnotation("更新音频")
    public int update(Audio audio);
    @LogAnnotation("获取一个音频")
    public Audio getOne(String id);
    @LogAnnotation("分页查询音频")
    public Map<String,Object> getAll(Integer page, Integer rows);
    @LogAnnotation("查询所有音频")
    public List<Audio> getAllAudio();
}
