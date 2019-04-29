package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Article;

import java.util.List;

public interface ArticleDAO {
    public Article selectOne(String id);
    public List<Article> selectAllByGuru(String id);
    public List<Article> selectAllByGuruNew(String id);
    public List<Article> selectAll();
    public List<Article> selectAllAritle(Integer start,Integer rows);
    public int selectCount();
    public int insert(Article article);

    public int deletes(String[] ids);

    public int update(Article article);
}
