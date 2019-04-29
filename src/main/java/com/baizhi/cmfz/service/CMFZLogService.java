package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.CMFZLog;

import java.util.Map;

public interface CMFZLogService {
    @LogAnnotation("分页查询日志")
    public Map<String,Object> getAll(Integer page,Integer rows);
    @LogAnnotation("删除日志")
    public int deletes(int[] ids);
    @LogAnnotation("添加日志")
    public int addCMFZLog(CMFZLog cmfzLog);
    @LogAnnotation("导出日志")
    public int outCMFZLog();

}
