package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Audio;

import java.util.List;

public interface AudioDAO {
    public List<Audio> selectAllByAlbumId(String id);
    public Audio selectOne(String id);
    public List<Audio> selectAll(Integer start,Integer rows);
    public List<Audio> selectAllAudio();
    public int insert(Audio audio);
    public int deletes(String[] ids);
    public int update(Audio audio);
    public int selectCount();
}
