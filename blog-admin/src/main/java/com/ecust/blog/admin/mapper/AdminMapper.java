package com.ecust.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecust.blog.admin.pojo.Admin;
import com.ecust.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:58
 */
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from ms_permission where id in (select permission_id from ms_admin_permission where admin_id=#{id})")
    List<Permission> findPermissionById(Long id);
}
