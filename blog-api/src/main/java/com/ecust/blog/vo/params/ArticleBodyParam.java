package com.ecust.blog.vo.params;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/5 - 9:47
 */
@Data
public class ArticleBodyParam {
    // markdown内容
    private String content;
    // html内容
    private String contentHtml;
}
