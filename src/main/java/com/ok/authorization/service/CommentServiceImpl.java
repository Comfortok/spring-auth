package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
import com.ok.authorization.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void setCommentDao(CommentRepository commentDao) {
        this.commentRepository = commentDao;
    }

    @Override
    @Transactional//remove id, replace with comment.getArticle
    public void createComment(Comment comment, long id) {
        this.commentRepository.createComment(comment, id);
    }

    @Override
    @Transactional
    public void removeComment(long id) {
        this.commentRepository.removeComment(id);
    }

    @Override
    @Transactional
    public void editComment(Comment comment) {
        this.commentRepository.editComment(comment);
    }

    @Override
    @Transactional
    public Comment getCommentById(long id) {
        return this.commentRepository.getCommentById(id);
    }

    @Override
    @Transactional
    public List<Comment> getAllComments(Article article) {
        return this.commentRepository.getAllComments(article);
    }
}
