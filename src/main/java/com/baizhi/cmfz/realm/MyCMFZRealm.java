package com.baizhi.cmfz.realm;

import com.baizhi.cmfz.dao.AdminDAO;
import com.baizhi.cmfz.dao.RoleDAO;
import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.Resource;
import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.utils.RoleUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * author:bobo大人
 * createDate:2018/8/13
 * createTime:20:06
 * description:
 */
public class MyCMFZRealm extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = authenticationToken.getPrincipal().toString();
        Admin admin = adminDAO.selectOneByUsername(username);
        if(admin != null){
            return new SimpleAuthenticationInfo(username,admin.getPassword(), ByteSource.Util.bytes(admin.getPasswordsalt()),this.getName());
        }

        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 1. 获取用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
        // 2. 根据用户名获取权限数据
        List<Role> roleList = roleDAO.selectAllRoleByAdmin(username);
        String[] usernames = new String[roleList.size()];
        int i = 0;
        for (Role role : roleList) {
            usernames[i] = role.getRolename();
            i++;
        }
        List<Resource> parentResources = roleDAO.selectParentResourcesByRoleName(usernames);

        for (Resource resource : parentResources) {
            RoleUtils.getSonResources(resource,roleDAO,usernames);
        }

        List<String> permissionList = new ArrayList<>();

        for(Resource resource : parentResources){
            if(resource.getCode()!=null && resource.getCode() != ""){
                permissionList.add(resource.getCode());
            }
            if(resource.getResourceList()!=null && resource.getResourceList().size()>0){
                for(Resource re : resource.getResourceList()){
                    if(re.getCode()!=null && re.getCode() != ""){
                        permissionList.add(re.getCode());
                    }
                    if(re.getResourceList()!=null && re.getResourceList().size()>0){
                        for(Resource r : re.getResourceList()){
                            if(r.getCode()!=null && r.getCode() != ""){
                                permissionList.add(r.getCode());
                            }
                        }
                    }
                }
            }
        }
        System.out.println(parentResources);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }



    private AdminDAO adminDAO;

    private RoleDAO roleDAO;

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }


}
