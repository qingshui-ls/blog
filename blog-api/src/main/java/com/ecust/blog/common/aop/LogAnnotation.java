package com.ecust.blog.common.aop;

import java.lang.annotation.*;

/**
 * @authour shuangliang
 * @date 2022/6/5 - 10:44
 */
/* ElementType.TYPE 代表可以放在类上, ElementType.METHOD代表放在方法上
 * */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";

    String operator() default "";
}
