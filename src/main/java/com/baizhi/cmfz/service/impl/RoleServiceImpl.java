package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ResourcesDAO;
import com.baizhi.cmfz.dao.RoleDAO;
import com.baizhi.cmfz.entity.Resource;
import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.service.RoleService;
import com.baizhi.cmfz.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/14
 * createTime:17:31
 * description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private ResourcesDAO resourcesDAO;
    @Override
    public Map<String, Object> getAll(Integer page, Integer rows) {

        Map<String,Object> map = new HashMap<>();

        int count = roleDAO.selectCount();
        Integer start = ( page - 1) * rows;
        List<Role> roles = roleDAO.selectAll(start, rows);

        for(Role r : roles){
            List<Resource> resources = roleDAO.selectParentResourceByRoleName(r.getRolename());
            for(Resource resource : resources){
                RoleUtils.getSonResource(resource,roleDAO,r.getRolename());
            }
            r.setResourceList(resources);
        }

        map.put("total",count);
        map.put("rows",roles);
        return map;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roles = roleDAO.selectAllRole();
        return roles;
    }

    @Override
    public int addRole(int[] ids, String rolename) {

        int i = roleDAO.insert(rolename);
        int j = 0;
        if(ids != null && ids.length>0){
            for(int id :ids){
                j = roleDAO.insertRoleResource(rolename, id);
            }
        }
        return i*j;
    }

    @Override
    public int getOneRole(Integer id) {
        return 0;
    }

    @Override
    public int deletes(int[] ids) {

        List<Role> roleList = roleDAO.selectRoles(ids);

        for (Role role : roleList) {
            roleDAO.deleteRoleResource(role.getRolename());
            roleDAO.deleteAdminRole(role.getId());
        }


        int i = roleDAO.deletes(ids);


        return 0;
    }

    @Override
    public int update(int[] ids, Role role,String newRoleName) {
        String oldRoleName = role.getRolename();
        if(newRoleName != null && newRoleName != ""){
            role.setRolename(newRoleName);
        }
        int i = roleDAO.update(role);
        roleDAO.deleteRoleResource(oldRoleName);
        for (int id: ids) {
            roleDAO.insertRoleResource(role.getRolename(),id);
        }

        return 0;
    }
}
