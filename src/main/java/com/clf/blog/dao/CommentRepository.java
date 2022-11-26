package com.clf.blog.dao;

import com.clf.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommentRepository {
    List<Comment> selectCommentsByBlogId(Long id);

    int saveComment(Comment comment);

    int deleteCommentByBlogId(Long blogId);

    Comment selectCommentByParentCommentId(Long id);

    int updateAdminComment(Comment comment);

    int updateUserComment(Comment comment);

    Long selectCommentCount(Long blogId);

    List<Comment> selectComments();

    int deleteComment(Long id);

    Long selectNickName(String nickname);

    int deleteCommentByNickName(String nickname);
}
