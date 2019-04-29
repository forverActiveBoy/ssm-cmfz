package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcesDAO {
    public List<Resource> selectAll();

    //查询顶级资源
    List<Resource> selectParentResource();
    // 查询当前资源的子级资源
    List<Resource> selectSonResource(Integer parentId);
}
