package com.ecust.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 12:52
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data1;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }
}
