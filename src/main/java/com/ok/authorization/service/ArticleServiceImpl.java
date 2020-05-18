package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.User;
import com.ok.authorization.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Article createArticle(Article article) {
        logger.info("Setting a current date to an article with header: " + article.getHeader());
        article.setReleaseDate(new Date());
        logger.info("Current date is " + article.getReleaseDate());
        article.setUser(getActiveUserFromCurrentSession());
        return articleRepository.createArticle(article);
    }

    private User getActiveUserFromCurrentSession() {
        User user = new User();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken) && (auth != null)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            user.setUsername(userDetail.getUsername());
        }
        return user;
    }

    @Override
    @Transactional
    @CacheEvict(value = "articles", allEntries = true)
    public boolean removeArticle(long id) {
        return this.articleRepository.removeArticle(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "articles", allEntries = true)
    public Article editArticle(Article article) {
        return this.articleRepository.editArticle(article);
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

    @Override
    @Transactional
    public List<Article> getAllArticlesSortedByHeader() {
        return this.articleRepository.getAllArticlesSortedByHeader();
    }

    @Override
    @Transactional
    public List<Article> getAllArticlesSortedByTextSize() {
        return this.articleRepository.getAllArticlesSortedByTextSize();
    }
}