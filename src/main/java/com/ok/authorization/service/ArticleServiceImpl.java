package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public void createArticle(Article article) {
        this.articleRepository.createArticle(article);
    }

    @Override
    @Transactional
    public void removeArticle(long id) {
        this.articleRepository.removeArticle(id);
    }

    @Override
    @Transactional
    public void editArticle(Article article) {
        this.articleRepository.editArticle(article);
    }

    @Override
    @Transactional
    public Article getArticleById(long id) {
        return this.articleRepository.getArticleById(id);
    }

    @Override
    @Transactional
    public List<Article> getAllArticles() {
        return this.articleRepository.getAllArticles();
    }
}