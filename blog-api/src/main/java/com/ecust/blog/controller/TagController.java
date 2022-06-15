package com.ecust.blog.controller;


import com.ecust.blog.service.TagService;
import com.ecust.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 14:24
 */
// 代表返回json数据
@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/hot")
    public Result hot() {
        int limit = 6;
        // 标签拥有的文章数量最多 即 最热标签
        // 查询 根据tag_id分组 计数,从大到小排序 取前limit个1
        return tagService.hots(limit);
    }

    @GetMapping
    public Result findAll() {
        return tagService.findAll();
    }

    @GetMapping("detail")
    public Result findAllDetail() {
        return tagService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id) {
        return tagService.findDetailById(id);
    }

}
