package com.ok.authorization.repository;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;

import java.util.List;

public interface CommentRepository {
    Comment createComment(Comment comment, long id);
    boolean removeComment(long id);
    Comment editComment(Comment comment);
    Comment getCommentById(long id);
    List<Comment> getAllComments(Article article);
}
