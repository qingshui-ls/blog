package com.ecust.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecust.blog.dao.mapper.ArticleMapper;
import com.ecust.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 21:41
 */
@Service
public class ThreadService {

    // 希望此操作在线程池执行 ,不会影响原有的主线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        Integer viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 根据id更新
        queryWrapper.eq(Article::getId, article.getId());
        // 为了在多线程环境下,线程安全
        queryWrapper.eq(Article::getViewCounts, viewCounts);
        articleMapper.update(article, queryWrapper);
        /* //测试代码:
        try {
            Thread.sleep(5000);
            System.out.println("更新完成了...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
}
