package com.ecust.blog.service;

import com.ecust.blog.dao.pojo.SysUser;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.UserVo;
import org.springframework.stereotype.Service;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 15:28
 */

public interface SysUserService {
    SysUser findSysUserById(Long authorId);

    SysUser findUser(String account, String password);

    // 根据token查询用户信息
    Result findUserByToken(String token);

    // 查询该用户是否被注册
    SysUser findUserByAccount(String account);

    // 保存用户
    void save(SysUser sysUser);

    UserVo findUserVoById(Long authorId);
}
