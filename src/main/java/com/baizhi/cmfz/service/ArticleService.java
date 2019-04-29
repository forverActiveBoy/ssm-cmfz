package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    @LogAnnotation("添加文章")
    public int addAritle(Article article);
    @LogAnnotation("删除文章")
    public int deletes(String[] id);
    @LogAnnotation("更新文章")
    public int update(Article article);
    @LogAnnotation("分页查询文章")
    public Map<String,Object> getAll(Integer page, Integer rows);
    @LogAnnotation("查询所有文章")
    public List<Article> getAll();
    @LogAnnotation("查询一篇文章")
    public Article getOne(String id);
}
