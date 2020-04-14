package com.ok.authorization.repository;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
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

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createComment(Comment comment, long id) {
        entityManager.createNativeQuery("INSERT INTO COMMENTS(TEXT, ARTICLE_ID, USERNAME) VALUES(?, ?, ?)")
                .setParameter(1, comment.getText())
                .setParameter(2, id)
                .setParameter(3, comment.getUser().getUsername())
                .executeUpdate();
        System.out.println("Comment dao. Comment was created with id " + comment.getId());
        logger.info("Comment " + comment + " is successfully created.");
    }

    @Override
    public void removeComment(long id) {
        Comment comment = getCommentById(id);
        sessionFactory.getCurrentSession().delete(comment);
        logger.info("Comment " + comment + " is successfully removed.");
    }

    @Override
    public void editComment(Comment comment) {
        sessionFactory.getCurrentSession().merge(comment);
        logger.info("Comment " + comment + " is successfully updated.");
    }

    @Override
    public Comment getCommentById(long id) {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    public List<Comment> getAllComments(Article article) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getCommentsByArticleId");
        query.setParameter("id", article.getId());
        List<Comment> result = query.getResultList();
        logger.info("All comments are successfully loaded.");
        return result;
    }
}
