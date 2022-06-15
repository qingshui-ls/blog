package com.ecust.blog.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecust.blog.dao.dos.Archives;
import com.ecust.blog.dao.pojo.Article;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 12:36
 */

public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();

    IPage<Article> listArticle(Page<Article> page,
                               Long categoryId,
                               Long tagId,
                               String year,
                               String month);
}