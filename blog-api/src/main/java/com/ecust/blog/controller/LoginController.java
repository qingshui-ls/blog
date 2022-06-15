package com.ecust.blog.controller;

import com.ecust.blog.service.LoginService;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 14:44
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        // 登录验证用户
        return loginService.login(loginParam);
    }
}
