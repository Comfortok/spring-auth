package com.ok.authorization.service;

import com.ok.authorization.model.Article;

import java.util.List;

public interface ArticleService {
    void createArticle(Article article);
    void removeArticle(long id);
    void editArticle(Article article);
    Article getArticleById(long id);
    List<Article> getAllArticles();
}