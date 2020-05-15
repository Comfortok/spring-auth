package com.ok.authorization.repository;

import com.ok.authorization.config.AppConfig;
import com.ok.authorization.model.Article;
import com.ok.authorization.model.ArticleBuilder;
import com.ok.authorization.model.User;
import com.ok.authorization.model.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
public class ArticleRepositoryImplTest {

    @Autowired
    private ArticleRepository articleRepository;
    private Article article;

    @Before
    public void init() {
        User user = new UserBuilder()
                .withUsername("TUsername")
                .build();
        article = new ArticleBuilder()
                .withHeader("THeader")
                .withReleaseDate(new Date())
                .withText("TText")
                .withUser(user)
                .build();
    }

    @Test
    public void shouldCreateAnArticle() {
        List<Article> articlesBeforeAddingNewArticle = articleRepository.getAllArticles();
        int articlesSizeBeforeAddingArticle = articlesBeforeAddingNewArticle.size();
        articleRepository.createArticle(article);
        List<Article> articlesAfterAddingNewArticle = articleRepository.getAllArticles();
        int articleSizeAfterAddingArticle = articlesAfterAddingNewArticle.size();
        Assert.assertEquals(articlesSizeBeforeAddingArticle, articleSizeAfterAddingArticle - 1);
    }

    @Test
    public void shouldRemoveArticle() {
        articleRepository.createArticle(article);
        List<Article> articles = articleRepository.getAllArticles();
        Article addedArticle = articles.stream()
                .filter(a -> a.getHeader().equals(article.getHeader())).findAny().orElse(null);
        articleRepository.removeArticle(addedArticle.getId());
        Assert.assertEquals(articles.size(), articleRepository.getAllArticles().size() + 1);
    }

    @Test
    public void shouldUpdateArticle() {
        articleRepository.createArticle(article);
        List<Article> articles = articleRepository.getAllArticles();
        Article addedArticle = articles.stream()
                .filter(a -> a.getHeader().equals(article.getHeader())).findAny().orElse(null);
        Article newArticle = new ArticleBuilder()
                .withId(addedArticle.getId())
                .withHeader("NewHeader")
                .withReleaseDate(new Date())
                .withText("NewText")
                .build();
        Article updatedArticle = articleRepository.editArticle(newArticle);
        Assert.assertEquals(addedArticle.getId(), updatedArticle.getId());
        Assert.assertEquals(updatedArticle.getHeader(), "NewHeader");
    }

    @Test
    public void shouldGetArticleById() {
        articleRepository.createArticle(article);
        List<Article> articles = articleRepository.getAllArticles();
        Article addedArticle = articles.stream()
                .filter(a -> a.getHeader().equals(article.getHeader())).findAny().orElse(null);
        Article articleFromDB = articleRepository.getArticleById(addedArticle.getId());
        Assert.assertEquals(addedArticle.getId(), articleFromDB.getId());
        Assert.assertEquals(addedArticle.getHeader(), articleFromDB.getHeader());
    }
}