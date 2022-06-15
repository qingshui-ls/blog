package com.ecust.blog.vo;

import lombok.Data;

import java.time.Period;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 16:00
 */
@Data
public class LoginUserVo {
    private String account;
    private String nickname;
    private String avatar;
    private String id;
}
