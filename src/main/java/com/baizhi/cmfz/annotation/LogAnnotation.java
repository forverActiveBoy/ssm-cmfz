package com.baizhi.cmfz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author:bobo大人
 * createDate:2018/8/9
 * createTime:14:15
 * description:
 */
@Target(ElementType.METHOD)//该注解的作用地点   作用在方法上
@Retention(RetentionPolicy.RUNTIME)//该注解会存在class字节码中
public @interface LogAnnotation {
    public String value() default "";
}
