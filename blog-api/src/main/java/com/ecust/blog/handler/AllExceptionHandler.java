package com.ecust.blog.handler;

import com.ecust.blog.vo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 17:57
 */
// 对加了@controller注解的方法进行拦截处理 Aop实现
@ControllerAdvice
public class AllExceptionHandler {
    // 处理Exception.class异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json数据
    public Result doException(Exception ex) {

        ex.printStackTrace();
        return Result.fail(-999, "系统异常");

    }
}
