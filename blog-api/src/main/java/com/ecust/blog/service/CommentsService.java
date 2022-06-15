package com.ecust.blog.service;

import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.params.CommentParam;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 22:43
 */
public interface CommentsService {

    //    根据文章id查询评论列表
    Result commentsByArticleId(Long id);

    // 发布评论
    Result comment(CommentParam commentParam);
}
