package com.ecust.blog.service;

import com.ecust.blog.vo.ArticleBodyVo;
import com.ecust.blog.vo.ArticleVo;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.params.ArticleBodyParam;
import com.ecust.blog.vo.params.ArticleParam;
import com.ecust.blog.vo.params.PageParams;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 12:58
 */

public interface ArticleService {
    Result listArticle(PageParams params);

    Result hotArticle(int limit);

    Result newArticle(int limit);

    Result listArchives();

    // 返回文章内容
    Result findArticleById(Long id);

    //文章发布
    Result publish(ArticleParam articleParam);

}
