package com.ecust.blog.service;

import com.ecust.blog.dao.pojo.SysUser;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.params.LoginParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 14:49
 */
// 注册用户添加事务
@Transactional
@Service
public interface LoginService {
    // 登录
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    // 推出登录
    Result logout(String token);

    // 注册
    Result register(LoginParam loginParam);
}
