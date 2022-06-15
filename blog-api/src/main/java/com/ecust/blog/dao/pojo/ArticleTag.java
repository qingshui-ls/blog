package com.ecust.blog.dao.pojo;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/5 - 10:03
 */
// 文章和tag的关联表
@Data
public class ArticleTag {
    private Long id;
    private Long articleId;
    private Long tagId;
}
