package com.ecust.blog.vo;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 20:01
 */
@Data
public class CategoryVo {
    //id，图标路径，图标名称
    private String id;

    private String avatar;

    private String categoryName;
    private String description;
}
