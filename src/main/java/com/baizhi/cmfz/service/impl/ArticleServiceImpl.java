package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ArticleDAO;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/6
 * createTime:20:13
 * description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO ad;
    @Override
    public int addAritle(Article article) {
        return ad.insert(article);
    }

    @Override
    public int deletes(String[] ids) {
        return ad.deletes(ids);
    }

    @Override
    public int update(Article article) {
        return ad.update(article);
    }

    @Override
    public Map<String,Object> getAll(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Article> articleList = ad.selectAllAritle(start, rows);
        int count = ad.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",articleList);
        return map;
    }

    @Override
    public List<Article> getAll() {
        return ad.selectAll();
    }

    @Override
    public Article getOne(String id) {
        return ad.selectOne(id);
    }
}
