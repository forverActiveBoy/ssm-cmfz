package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    @LogAnnotation("添加轮播图")
    public int addBanner(Banner banner);
    @LogAnnotation("删除轮播图")
    public int deleteBanners(int[] ids);
    @LogAnnotation("更新轮播图")
    public int updateBanner(Banner banner);
    @LogAnnotation("分页获取轮播图")
    public Map<String,Object> getAll(Integer page, Integer rows);
    @LogAnnotation("查询一个轮播图")
    public Banner getBanner(Integer id);
}
