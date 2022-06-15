package com.ecust.blog.service;


import com.ecust.blog.dao.mapper.ArticleMapper;
import com.ecust.blog.dao.pojo.Article;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.TagVo;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 14:26
 */
public interface TagService {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int i);

    // 查询所有标签
    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
