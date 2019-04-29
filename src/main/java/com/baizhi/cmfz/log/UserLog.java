package com.baizhi.cmfz.log;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.CMFZLog;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.CMFZLogService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * author:bobo大人
 * createDate:2018/8/9
 * createTime:14:11
 * description:
 */
public class UserLog implements MethodInterceptor {
    @Autowired
    private CMFZLogService cmfzLogService;
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        CMFZLog cmfzLog = new CMFZLog();
        cmfzLog.setCreatedate(new Date());
        long start = System.currentTimeMillis();
        //获取方法
        Method method = methodInvocation.getMethod();
        //获取方法名
        String methodName = method.getName();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        //获取方法上的自定义注解内容
        String value = annotation.value();
        cmfzLog.setMethodname(value);

        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取session对象
        HttpSession session = request.getSession();

        Object[] arguments = methodInvocation.getArguments();

        cmfzLog.setUsername("xiaohei......测试");

        //目标方法的返回值
        Object proceed = null;
        try {
            proceed = methodInvocation.proceed();
            cmfzLog.setResult("success");
            cmfzLog.setConsumetime((System.currentTimeMillis()-start)/1000F+"");
            cmfzLogService.addCMFZLog(cmfzLog);
        } catch (Throwable throwable) {
            cmfzLog.setConsumetime((System.currentTimeMillis()-start)/1000F+"");
            cmfzLog.setResult(throwable.getMessage());
            cmfzLogService.addCMFZLog(cmfzLog);
            throwable.printStackTrace();
        }

        return proceed;
    }
}
