package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment, long id);
    void removeComment(long id);
    void editComment(Comment comment);
    Comment getCommentById(long id);
    List<Comment> getAllComments(Article article);
}
