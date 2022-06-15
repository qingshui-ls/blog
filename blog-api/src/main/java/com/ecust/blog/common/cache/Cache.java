package com.ecust.blog.common.cache;



import java.lang.annotation.*;

/**
 * AOP缓存 1. 定义一个切面,切面定义了切点和通知的关系
 *
 * @authour shuangliang
 * @date 2022/6/5 - 22:52
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    long expire() default 1 * 60 * 1000;// 1分钟

    String name() default "";
}
