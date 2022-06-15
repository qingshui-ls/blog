package com.ecust.blog.admin.service;

import com.ecust.blog.admin.Vo.Result;
import com.ecust.blog.admin.model.params.PageParam;
import com.ecust.blog.admin.pojo.Permission;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:08
 */

public interface PermissionService {

    // 管理台 需要表中所有字段 Permission对象
    // 分页查询
    Result listPermission(PageParam pageParam);

    Result add(Permission permission);

    Result update(Permission permission);

    Result delete(Long id);
}
