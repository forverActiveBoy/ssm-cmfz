package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.AudioDAO;
import com.baizhi.cmfz.entity.Audio;
import com.baizhi.cmfz.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author:bobo大人
 * createDate:2018/8/8
 * createTime:22:01
 * description:
 */
@Service
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDAO audioDAO;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public int addAudio(Audio audio) {
        int i = 0;
        try {
            i = audioDAO.insert(audio);
            Set keys = redisTemplate.keys("audio**");
            redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deletes(String[] ids) {
        int i = 0;
        try {
            i = audioDAO.deletes(ids);
            Set keys = redisTemplate.keys("audio**");
            redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int update(Audio audio) {
        int i = 0;
        try {
            i = audioDAO.update(audio);
            Set keys = redisTemplate.keys("audio**");
            redisTemplate.delete(audio.getId());
            redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Audio getOne(String id) {

        Audio audio = null;
        try {
            audio = (Audio) redisTemplate.opsForValue().get(id);
            if(audio == null){
                audio = audioDAO.selectOne(id);
                redisTemplate.opsForValue().set(id,audio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audio;
    }

    @Override
    public Map<String,Object> getAll(Integer page, Integer rows) {
        List<Audio> audioList = null;
        Map<String,Object> map = new HashMap<>();
        try {
            Integer start = (page - 1) * rows;
            audioList = (List<Audio>)redisTemplate.opsForValue().get("audio-" + start + "-" + rows);
            if(audioList == null || audioList.size() == 0){
                audioList = audioDAO.selectAll(start, rows);
                redisTemplate.opsForValue().set("audio-" + start + "-" + rows,audioList);
            }
            map.put("rows",audioList);
            map.put("total",audioDAO.selectCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<Audio> getAllAudio() {
        List<Audio> audioList = (List<Audio>) redisTemplate.opsForValue().get("audio");
        if(audioList == null || audioList.size() == 0){
            audioList = audioDAO.selectAllAudio();
            redisTemplate.opsForValue().set("audio",audioList);
        }
        return audioList;
    }
}
