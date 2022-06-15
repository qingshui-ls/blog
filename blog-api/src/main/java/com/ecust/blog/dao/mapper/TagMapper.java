package com.ecust.blog.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecust.blog.dao.pojo.Tag;

import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 12:46
 */

public interface TagMapper extends BaseMapper<Tag> {
    //    根据文章id查询标签列表
    List<Tag> findTagsByArticleId(Long articleId);

    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
