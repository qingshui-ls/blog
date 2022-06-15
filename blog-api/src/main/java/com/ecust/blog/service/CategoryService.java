package com.ecust.blog.service;

import com.ecust.blog.vo.CategoryVo;
import com.ecust.blog.vo.Result;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 20:25
 */
public interface CategoryService {
    Result findAll();

    CategoryVo findCategoryById(Long categoryId);

    Result findAllDetail();

    Result categoryDetailById(Long id);
}
