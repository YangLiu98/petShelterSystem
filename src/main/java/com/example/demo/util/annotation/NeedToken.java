package com.example.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedToken {
    /**
     * 是否需要权限
     *
     * @return 是否需要权限
     */
    boolean required() default true;

    /**
     * 需要的是什么权限
     *
     * @return 权限编号
     */
    String function();

    /**
     * 起草合同
     */
    String Admin = "0";

    /**
     * 定稿合同
     */
    String Logged_User = "1";
}
