package com.ecust.blog.admin.service.impl;


import com.ecust.blog.admin.pojo.Admin;
import com.ecust.blog.admin.pojo.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 权限认证
 *
 * @authour shuangliang
 * @date 2022/6/6 - 13:06
 */
@Service
public class AuthService {
    @Autowired
    private AdminService adminService;

    public boolean auth(HttpServletRequest request, Authentication authentication) {
        // 请求路径
        String requestURI = request.getRequestURI();
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)) {
            //未登录
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        Admin admin = adminService.findAdminByUsername(username);
        if (admin == null) {
            return false;
        }
        if (1 == admin.getId()) {
            // 1表示超级管理员
            return true;
        }
        Long id = admin.getId();
        requestURI = StringUtils.split(requestURI, '?')[0];
        List<Permission> permissionList = this.adminService.findPermissionById(id);
        for (Permission permission : permissionList) {
            if (requestURI.equals(permission.getPath())) {
                return true;
            }
        }
        return false;
    }
}
