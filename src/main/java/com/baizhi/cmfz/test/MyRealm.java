package com.baizhi.cmfz.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * author:bobo大人
 * createDate:2018/8/13
 * createTime:13:41
 * description:
 */
public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = authenticationToken.getPrincipal().toString();

        if(username.equals("lizhaoyi")){
            System.out.println(this.getName());
            return new SimpleAuthenticationInfo(username,"12345",this.getName());
        }

        return null;
    }
}
