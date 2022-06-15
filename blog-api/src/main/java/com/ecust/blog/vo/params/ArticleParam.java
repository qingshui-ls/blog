package com.ecust.blog.vo.params;

import com.ecust.blog.dao.pojo.Category;
import com.ecust.blog.vo.CategoryVo;
import com.ecust.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/5 - 9:45
 */
// 发布文章post请求参数
@Data
public class ArticleParam {
    private Long id;
    private ArticleBodyParam body;
    private Category category;
    private String summary;
    private List<TagVo> tags;
    private String title;
}
