package com.baizhi.cmfz.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * author:bobo大人
 * createDate:2018/8/13
 * createTime:13:39
 * description:
 */
public class ShiroTest {

    public static void main(String[] args) {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_main.ini");

        SecurityManager manager = factory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lizhaoyi","12345");


        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
    }


}
