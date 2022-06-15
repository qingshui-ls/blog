package com.ecust.blog.admin.controller;

import com.ecust.blog.admin.Vo.Result;
import com.ecust.blog.admin.model.params.PageParam;
import com.ecust.blog.admin.pojo.Permission;
import com.ecust.blog.admin.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:02
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam) {
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }

}
