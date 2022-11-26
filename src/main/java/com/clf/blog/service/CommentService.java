package com.clf.blog.service;

import com.clf.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectCommentsByBlogId(Long id);

    int saveComment(Comment comment);

    int deleteCommentByBlogId(Long blogId);

    int updateAdminComment(Comment comment);

    int updateUserComment(Comment comment);

    Long selectCommentCount(Long blogId);

    List<Comment> selectComments();

    int deleteComment(Long id);
}
