package com.ecust.blog.vo;

import org.apache.catalina.valves.ErrorReportValve;
import org.omg.CORBA.NO_PERMISSION;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 15:00
 */
// 定义异常类
public enum ErrorCode {

    SYSTEM_ERROR(-999, "系统错误"),
    PARAMS_ERROR(10001, "参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    TOKEN_ERROR(10003, "TOKEN不合法"),
    ACCOUNT_EXIST(10004, "账户已经存在"),
    NO_PERMISSION(70001, "无访问权限"),
    SESSIO_TIME_OUT(90001, "会话超时"),
    NO_LOGIN(90002, "未登录"),
    UPLOAD_ERROR(20001, "上传失败");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
