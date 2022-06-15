package com.ecust.blog.controller;

import com.ecust.blog.dao.pojo.SysUser;
import com.ecust.blog.utils.UserThreadLocal;
import com.ecust.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
//        SysUser
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }

}

