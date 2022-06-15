package com.ecust.blog.controller;

import com.ecust.blog.common.aop.LogAnnotation;
import com.ecust.blog.service.ArticleService;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.params.ArticleParam;
import com.ecust.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @authour shuangliang
 * @date 2022/6/2 - 12:47
 */
// json数据交互
@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /* 首页文章列表
    @param pageParams
    @Return
    @LogAnnotation 代表对此接口记录日志
    * */
    @PostMapping
    @LogAnnotation(module = "文章", operator = "获取文章列表")
//    @Cache(expire = 5 * 60 * 1000, name = "list_article")
    public Result listArticle(@RequestBody PageParams pageParams) {
        //ArticleVo 页面接收的数据
        return articleService.listArticle(pageParams);
    }

    /* 热门文章列表
    @param pageParams
    @Return
    * */
//    @Cache(expire = 5 * 60 * 1000, name = "hot_article")
    @PostMapping("hot")
    public Result hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    // 最热文章
//    @Cache(expire = 5 * 60 * 1000, name = "new_article")
    @PostMapping("new")
    public Result newArticle() {
        int limit = 5;
        return articleService.newArticle(limit);
    }

    // 文章归档
    @PostMapping("/listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long id) {
        return articleService.findArticleById(id);
    }

    // 发布文章
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }

}
