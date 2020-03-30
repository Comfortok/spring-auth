package com.ok.authorization.repository;

import com.ok.authorization.model.Article;

import java.util.List;

public interface ArticleRepository {
    void createArticle(Article article);
    void removeArticle(long id);
    void editArticle(Article article);
    Article getArticleById(long id);
    List<Article> getAllArticles();
}