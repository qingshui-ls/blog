package com.ecust.blog.dao.pojo;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 22:50
 */
@Data
public class Comment {
    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
