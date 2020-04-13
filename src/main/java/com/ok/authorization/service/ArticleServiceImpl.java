package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    @CacheEvict(value = "articles", allEntries = true)
    public void createArticle(Article article)
    {
        article.setReleaseDate(new Date());
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
        System.out.println("getting an article in service");
        return this.articleRepository.getArticleById(id);
    }

    @Override
    @Transactional
    @Cacheable("articles")
    public List<Article> getAllArticles() {
        System.out.println("getting all articles is service");
        return this.articleRepository.getAllArticles();
    }
}