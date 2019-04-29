package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Banner;

import java.util.List;

public interface BannerDAO {
    public List<Banner> selectAll(Integer start,Integer rows);
    public int insert(Banner banner);
    public int deletes(int[] ids);
    public int update(Banner banner);
    public Banner selectShowBanner(Integer id);
    public int selectCount();
}
