package com.ecust.blog.dao.pojo;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 19:52
 */
@Data
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
