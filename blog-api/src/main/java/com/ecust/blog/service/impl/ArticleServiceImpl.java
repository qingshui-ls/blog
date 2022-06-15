package com.ecust.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecust.blog.dao.dos.Archives;
import com.ecust.blog.dao.mapper.ArticleBodyMapper;
import com.ecust.blog.dao.mapper.ArticleMapper;
import com.ecust.blog.dao.mapper.ArticleTagMapper;
import com.ecust.blog.dao.pojo.Article;
import com.ecust.blog.dao.pojo.ArticleBody;
import com.ecust.blog.dao.pojo.ArticleTag;
import com.ecust.blog.dao.pojo.SysUser;
import com.ecust.blog.service.ArticleService;
import com.ecust.blog.service.CategoryService;
import com.ecust.blog.service.SysUserService;
import com.ecust.blog.service.TagService;
import com.ecust.blog.utils.UserThreadLocal;
import com.ecust.blog.vo.*;
import com.ecust.blog.vo.params.ArticleParam;
import com.ecust.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 13:00
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Resource
    private ArticleBodyMapper articleBodyMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ThreadService threadService;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public Result listArticle(PageParams params) {
        Page<Article> page = new Page<>(params.getPage(), params.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(
                page,
                params.getCategoryId(),
                params.getTagId(),
                params.getYear(),
                params.getMonth());
        List<Article> records = articleIPage.getRecords();
        return Result.success(copyList(records, true, true));
    }

    /*@Override
    public Result listArticle(PageParams params) {
//        分页查询 article数据库表
        Page<Article> page = new Page<>(params.getPage(), params.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

//        根据类别分类
        if (params.getCategoryId() != null) {
            queryWrapper.eq(Article::getCategoryId, params.getCategoryId());
        }

//       根据标签分类
        if (params.getTagId() != null) {
            // 加入标签 条件查询
            // article表中没有tag字段 一篇文章有多个标签
            // article_tag article_id 1:n 用tag_id关联
            LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            articleTagLambdaQueryWrapper.eq(ArticleTag::getTagId, params.getTagId());
            List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagLambdaQueryWrapper);
            if (articleTags.size() > 0) {
                List<Long> articleIdList = new ArrayList<>();
                for (ArticleTag articleTag : articleTags) {
                    articleIdList.add(articleTag.getArticleId());
                }
                System.out.println(articleIdList);
                queryWrapper.in(Article::getId, articleIdList);
            }
        }


        // 是否置顶排序
        // order by createDate desc
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        // 不能直接返回
        List<ArticleVo> articleVoList = copyList(records, true, true);
        return Result.success(articleVoList);
    }*/

    //热门文章5个
    @Override
    public Result hotArticle(int limit) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Article::getViewCounts);
        lambdaQueryWrapper.select(Article::getId, Article::getTitle);
        // limit 后有空格
        lambdaQueryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles, false, false));
    }

    // 最热文章
    @Override
    public Result newArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(articles)) {
            return Result.success(Collections.emptyList());
        }
        List<ArticleVo> articleVoList = copyList(articles, false, false);
        return Result.success(articles);
    }


    // 文章归档
    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>(records.size());
        // 循环转化,将数据库查询到的数据转为Vo对象进行显示
        for (Article record : records) {
            articleVoList.add(copy(record, isTag, isAuthor, false, false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>(records.size());
        for (Article record : records) {
            articleVoList.add(copy(record, isTag, isAuthor, isBody, isCategory));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        // 将article中与articleVo相同属性进行copy,赋值给articleVo
        BeanUtils.copyProperties(article, articleVo);
        // article中getCreateDate()返回值是String类型,需进行转化
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        // 并不是所有接口都需要标签,作者信息
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findSysUserById(authorId).getNickname());
        }
        //具体内容
        if (isBody) {
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        // 分类
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            CategoryVo categoryVo = categoryService.findCategoryById(categoryId);
        }
        return articleVo;
    }


    @Override
    public Result findArticleById(Long id) {
        /*  1.根据id查询文章信息
            2.根据bodyId和categoryId关联查询
        * */
        Article article = articleMapper.selectById(id);
        ArticleVo articleVo = copy(article, true, true, true, true);

        /*
        * 查看完文章了，新增阅读数，有没有问题呢？
          查看完文章之后，本应该直接返回数据了，这时候做了一个更新操作，更新时    加写锁
          阻塞其他的读操作，性能就会比较低
          更新 增加了此次接口的 耗时 如果一旦更新出问题，不能影响 查看文章的操作
          线程池  可以把更新操作 扔到线程池中去执行，和主线程就不相关了
       threadService.updateArticleViewCount(articleMapper,article);
        * */
        threadService.updateArticleViewCount(articleMapper, article);
        return Result.success(articleVo);
    }


    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }

    @Override
    @Transactional
    public Result publish(ArticleParam articleParam) {
        /*  1. 发布文章:构建Article对象
            2. 作者id 当前登录用户 User.ThreadLocal().get(); 需要此接口加入拦截器中
            3. 获得标签 将标签加入关联列表中
            4. 文章内容存储 article bodyId 关联
        * */
        SysUser sysUser = UserThreadLocal.get();
        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setWeight(Article.Article_Common);
        article.setViewCounts(0);
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setCategoryId(Long.parseLong(articleParam.getCategory().getId()));
        // 插入之后生成文章id
        // 然后自动填入article对象
        this.articleMapper.insert(article);
        // tag
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            Long articleId = article.getId();
            for (TagVo tag : tags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(articleId);
                articleTag.setTagId(Long.parseLong(tag.getId()));
                articleTagMapper.insert(articleTag);
            }
        }
        // body存储
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);
        article.setBodyId(articleBody.getId());
        // 更改了BodyId ===> null,变为插入后的主键,需要更新
        articleMapper.updateById(article);
        Map<String, String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return Result.success(map);
    }
}
