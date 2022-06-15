package com.ecust.blog.controller;

import com.ecust.blog.service.impl.CommentsServiceImpl;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 22:38
 */
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsServiceImpl commentsService;

    // 根据文章获取评论
    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long articleId) {

        return commentsService.commentsByArticleId(articleId);
    }

    // 发送评论
    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam) {
        return commentsService.comment(commentParam);
    }
}
