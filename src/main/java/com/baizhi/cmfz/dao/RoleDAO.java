package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Resource;
import com.baizhi.cmfz.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDAO {
    public int insert(String rolename);
    public int insertRoleResource(String rolename,int id);
    public int update(Role role);
    public int deletes(int[] ids);
    public int deleteAdminRole(int id);
    public Role selectOne(Integer id);
    public List<Role> selectRoles(int[] ids);
    public List<Role> selectAllRoleByAdmin(String username);
    public int selectCount();
    public int deleteRoleResource(String rolename);
    public List<Role> selectAll(Integer start,Integer rows);
    public List<Role> selectAllRole();
    //   根据角色名查询顶级资源
    List<Resource> selectParentResourceByRoleName(String roleName);
    List<Resource> selectParentResourcesByRoleName(String[] roleName);

    // 查询当前资源的子级资源
    List<Resource> selectSonResource(@Param("parentId") Integer parentId, @Param("roleName") String roleName);
    List<Resource> selectSonResources(@Param("parentId") Integer parentId, @Param("roleNames") String[] roleNames);



}
