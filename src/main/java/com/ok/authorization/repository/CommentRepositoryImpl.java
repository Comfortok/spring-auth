package com.ok.authorization.repository;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {
    private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryImpl.class);
    private static final String INSERT_COMMENT_TO_DB = "INSERT INTO COMMENTS(TEXT, ARTICLE_ID, USERNAME) " +
            "VALUES(?, ?, ?)";
    private static final String GET_ALL_COMMENTS_BY_ARTICLE_ID = "getCommentsByArticleId";

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createComment(Comment comment, long id) {
        logger.warn("Adding a comment to database with id " + id);
        try {
            entityManager.createNativeQuery(INSERT_COMMENT_TO_DB)
                    .setParameter(1, comment.getText())
                    .setParameter(2, id)
                    .setParameter(3, comment.getUser().getUsername())
                    .executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while adding a comment. ", e);
        }
        logger.info("A comment with id " + comment.getId() + " is successfully added.");
    }

    @Override
    public void removeComment(long id) {
        logger.warn("Getting a comment with id " + id + " to delete");
        Comment comment = getCommentById(id);
        try {
            sessionFactory.getCurrentSession().delete(comment);
        } catch (HibernateException e) {
            logger.error("An exception has happened while deleting a comment. ", e);
        }
        logger.info("A comment with id " + comment.getId() + " is successfully deleted.");
    }

    @Override
    public void editComment(Comment comment) {
        logger.warn("Editing a comment with id " + comment.getId());
        try {
            sessionFactory.getCurrentSession().merge(comment);
        } catch (HibernateException e) {
            logger.error("An exception has happened while editing a comment. ", e);
        }
        logger.info("Comment with id " + comment.getId() + " is successfully updated.");
    }

    @Override
    public Comment getCommentById(long id) {
        logger.warn("Getting a comment from database with id " + id);
        Comment comment = null;
        try {
            comment = sessionFactory.getCurrentSession().get(Comment.class, id);
        } catch (HibernateException e) {
            logger.error("An exception has happened while getting a comment with id " + id, e);
        }
        logger.info("Comment with id " + id + " is successfully loaded.");
        return comment;
    }

    @Override
    public List<Comment> getAllComments(Article article) {
        List<Comment> result = null;
        logger.warn("Getting all comments from database");
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery(GET_ALL_COMMENTS_BY_ARTICLE_ID);
            query.setParameter("id", article.getId());
            result = query.getResultList();
        } catch (HibernateException e) {
            logger.error("An exception has happened while getting all comments from database. ", e);
        }
        logger.info("All comments are successfully loaded.");
        return result;
    }
}