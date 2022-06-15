package com.ecust.blog.admin.Vo;

import lombok.Data;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:12
 */
@Data
public class PageResult<T> {
    private List<T> list;
    private Long total;
}
