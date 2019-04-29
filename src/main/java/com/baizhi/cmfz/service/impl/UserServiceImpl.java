package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.UserDAO;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/10
 * createTime:11:28
 * description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;

        List<User> userList = userDAO.selectAll(start, rows);
        int count = userDAO.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",userList);
        return map;
    }

    @Override
    public int update(User user) {
        return userDAO.update(user);
    }

    @Override
    public int addUser(User user) {
        return userDAO.insert(user);
    }

    @Override
    public int deletes(int[] ids) {
        return userDAO.deletes(ids);
    }

    @Override
    public List<User> exportUser(int[] ids) {
        List<User> users = userDAO.selectAllById(ids);

        return users;
    }

    @Override
    public List<User> exportUser() {
        return userDAO.selectAllUser();
    }

    @Override
    public int importUser(List<User> userList) {
        int i = 0;
        for (User user:userList) {
            i += userDAO.importInsert(user);
        }
        return i;

    }

}
