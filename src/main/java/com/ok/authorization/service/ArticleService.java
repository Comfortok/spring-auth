package com.ok.authorization.service;

import com.ok.authorization.model.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article article);
    boolean removeArticle(long id);
    Article editArticle(Article article);
    Article getArticleById(long id);
    List<Article> getAllArticles();
    List<Article> getAllArticlesSortedByHeader();
    List<Article> getAllArticlesSortedByTextSize();
}