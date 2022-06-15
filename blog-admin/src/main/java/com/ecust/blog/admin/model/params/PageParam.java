package com.ecust.blog.admin.model.params;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/6 - 12:06
 */
@Data
public class PageParam {
    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
