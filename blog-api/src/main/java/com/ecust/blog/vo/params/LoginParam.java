package com.ecust.blog.vo.params;

import lombok.Data;


/**
 * @authour shuangliang
 * @date 2022/6/4 - 14:51
 */
@Data
public class LoginParam {
    private String account;
    private String password;
    private String nickname;
}
