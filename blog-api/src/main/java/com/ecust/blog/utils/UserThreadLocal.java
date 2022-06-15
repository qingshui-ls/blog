package com.ecust.blog.utils;

import com.ecust.blog.dao.pojo.SysUser;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 18:10
 */
public class UserThreadLocal {
    private UserThreadLocal() {
    }

    // 线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
