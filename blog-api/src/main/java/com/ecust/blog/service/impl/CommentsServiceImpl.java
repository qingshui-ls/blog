package com.ecust.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ecust.blog.dao.mapper.CommentMapper;
import com.ecust.blog.dao.pojo.Comment;
import com.ecust.blog.dao.pojo.SysUser;
import com.ecust.blog.service.ArticleService;
import com.ecust.blog.service.CommentsService;
import com.ecust.blog.service.SysUserService;
import com.ecust.blog.utils.UserThreadLocal;
import com.ecust.blog.vo.CommentVo;
import com.ecust.blog.vo.Result;
import com.ecust.blog.vo.UserVo;
import com.ecust.blog.vo.params.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 22:41
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentMapper commentMapper;
    @Autowired
    private SysUserService sysUserService;

    public Result commentsByArticleId(Long id) {
        /*  1.根据文章id查询  评论列表 从comment表
            2.根据作者id查询作者信息
            3.判断 如果 level =1 要查询它有没有子评论
            4.如果有 根据评论id 进行查询(parent_id)
         * */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        queryWrapper.eq(Comment::getLevel, 1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        // 从ThreadLocal中获取User对象
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        //如果是空，parent就是0
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>(comments.size());
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        commentVo.setId(String.valueOf(comment.getId()));
        BeanUtils.copyProperties(comment, commentVo);
        // 作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVoById = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVoById);
        // 子评论
        Integer level = comment.getLevel();
        if (1 == level) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        // to User 给谁评论
        if (level > 1) {
            Long toUid = comment.getToUid();
            UserVo toUserVo = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel, 2);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}
