package com.ecust.blog.controller;

import com.ecust.blog.service.SysUserService;
import com.ecust.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @authour shuangliang
 * @date 2022/6/4 - 15:37
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        return Result.success(sysUserService.findUserByToken(token));
    }
}
