package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.BannerDAO;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/5
 * createTime:14:10
 * description:
 */
@Service
public  class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bd;
    @Override
    public int addBanner(Banner banner) {

        return bd.insert(banner);
    }

    @Override
    public int deleteBanners(int[] ids) {
        return bd.deletes(ids);
    }

    @Override
    public int updateBanner(Banner banner) {
        return bd.update(banner);
    }

    @Override
    public Map<String,Object> getAll(Integer page,Integer rows) {
        Integer start = (page - 1) * rows;
        List<Banner> bannerList = bd.selectAll(start, rows);
        int count = bd.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",bannerList);
        return map;
    }

    @Override
    public Banner getBanner(Integer id) {
        return bd.selectShowBanner(id);
    }
}
