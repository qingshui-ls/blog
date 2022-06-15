package com.ecust.blog.admin.service.impl;


import com.ecust.blog.admin.pojo.Admin;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:55
 */
@Service
public class SecurityUserServiceImpl implements UserDetailsService {
    @Resource
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 登录时,会把username传递到这里
        // 通过username查询 admin表,如果admin存在,将密码告诉SpringSecurity
        // 如果不存在,则返回null 认证失败了
        Admin admin = this.adminService.findAdminByUsername(username);
        if (admin == null) {
            // 登录失败
            return null;
        }
        return new User(username, admin.getPassword(), new ArrayList<>());
    }
}
