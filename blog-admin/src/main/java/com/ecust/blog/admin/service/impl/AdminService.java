package com.ecust.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecust.blog.admin.mapper.AdminMapper;
import com.ecust.blog.admin.pojo.Admin;
import com.ecust.blog.admin.pojo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:58
 */
@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    public Admin findAdminByUsername(String username){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        queryWrapper.last("limit 1");
        return adminMapper.selectOne(queryWrapper);
    }

    public List<Permission> findPermissionById(Long id) {
        //select * from ms_permission where id in (select permission_id from ms_admin_permission where admin_id=#{adminId})
        return adminMapper.findPermissionById(id);
    }
}
