package com.ok.authorization.repository;

import com.ok.authorization.model.Article;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class ArticleRepositoryImpl implements ArticleRepository {
    private static final Logger logger = LoggerFactory.getLogger(ArticleRepositoryImpl.class);
    private static final String INSERT_ARTICLE_TO_DB = "INSERT INTO ARTICLE(HEADER, TEXT, RELEASE_DATE, USERNAME) " +
            "VALUES(?,?,?,?)";
    private static final String ARTICLE_HEADER = "header";
    private static final String ARTICLE_TEXT = "text";
    private static final String ARTICLE_DATE = "releaseDate";
    private static final String ARTICLE_ID = "id";
    private static final String SELECT_ALL_ARTICLES_FROM_DB = "select e from Article e order by e.releaseDate desc";

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Article createArticle(Article article) {
        logger.warn("Adding an article to database with header: " + article.getHeader());
        try {
            entityManager.createNativeQuery(INSERT_ARTICLE_TO_DB)
                    .setParameter(1, article.getHeader())
                    .setParameter(2, article.getText())
                    .setParameter(3, article.getReleaseDate())
                    .setParameter(4, article.getUser().getUsername())
                    .executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while adding an article. ", e);
        }
        logger.info("An article with header '" + article.getHeader() + "' is successfully added.");
        return article;
    }

    @Override
    public boolean removeArticle(long id) {
        logger.warn("Loading an article with id " + id);
        try {
            Article article = sessionFactory.getCurrentSession().load(Article.class, id);
            if (article != null) {
                logger.warn("An article with id " + id + " has found. Deleting an article.");
                this.sessionFactory.getCurrentSession().delete(article);
            }
        } catch (HibernateException e) {
            logger.error("An exception has happened while deleting an article. ", e);
        }
        logger.info("An article with id " + id + " is successfully removed.");
        return true;
    }

    @Override
    public Article editArticle(Article article) {
        logger.info("Editing an article with id " + article.getId());
        try {
            CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            CriteriaUpdate<Article> update = criteriaBuilder.createCriteriaUpdate(Article.class);
            Root<Article> root = update.from(Article.class);
            update.set(root.get(ARTICLE_HEADER), article.getHeader());
            update.set(root.get(ARTICLE_TEXT), article.getText());
            update.set((root.get(ARTICLE_DATE)), article.getReleaseDate());
            update.where(criteriaBuilder.equal(root.get(ARTICLE_ID), article.getId()));
            this.entityManager.createQuery(update).executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while editing an article. ", e);
        }
        logger.info("An article with id " + article.getId() + " is successfully updated.");
        return article;
    }

    @Override
    public Article getArticleById(long id) {
        Object result = null;
        logger.warn("Getting an article from database with id " + id);
        try {
            Query query = sessionFactory.getCurrentSession().getNamedQuery("getAnArticleById");
            query.setParameter(ARTICLE_ID, id);
            result = query.getSingleResult();
        } catch (HibernateException e) {
            logger.error("An exception has happened while getting an article. ", e);
        }
        logger.info("An article with id " + id + " is successfully loaded.");
        return (Article) result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getAllArticles() {
        Query query = null;
        logger.warn("Getting all articles from database");
        try {
             query = entityManager.createQuery(SELECT_ALL_ARTICLES_FROM_DB);
        } catch (Exception e) {
            logger.error("An exception has happened while getting all articles. ", e);
        }
        logger.info("All articles were selected.");
        return (List<Article>) query.getResultList();
    }
}