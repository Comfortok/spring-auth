package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    @CacheEvict(value = "articles", allEntries = true)
    public void createArticle(Article article)
    {
        logger.info("Setting a current date to an article with id " + article.getId());
        article.setReleaseDate(new Date());
        logger.info("Current date is " + article.getReleaseDate());
        this.articleRepository.createArticle(article);
    }

    @Override
    @Transactional
    @CacheEvict(value = "articles", allEntries = true)
    public void removeArticle(long id) {
        this.articleRepository.removeArticle(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "articles", allEntries = true)
    public void editArticle(Article article) {
        this.articleRepository.editArticle(article);
    }

    @Override
    @Transactional
    @Cacheable("articles")
    public Article getArticleById(long id) {
        return this.articleRepository.getArticleById(id);
    }

    @Override
    @Transactional
    @Cacheable("articles")
    public List<Article> getAllArticles() {
        return this.articleRepository.getAllArticles();
    }
}