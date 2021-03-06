package com.ok.authorization.service;

import com.ok.authorization.config.core.SpringMvcInitializer;
import com.ok.authorization.config.core.SpringSecurityInitializer;
import com.ok.authorization.model.*;
import com.ok.authorization.repository.ArticleRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class})
public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;
    private Article article;
    private User user;

    @InjectMocks
    private ArticleService articleService = new ArticleServiceImpl();

    @Before
    public void setup() {
        user = new UserBuilder()
                .withUsername("TestUsername")
                .withPassword("TestPass")
                .build();

        article = new ArticleBuilder()
                .withId(10L)
                .withReleaseDate(new Date())
                .withHeader("Test header")
                .withText("Test text")
                .withUser(user)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateAnArticle() {
        Mockito.when(articleRepository.createArticle(isA(Article.class))).thenReturn(article);
        Article serviceArticle = articleService.createArticle(article);
        Assert.assertNotNull(serviceArticle);
        Assert.assertNotNull(serviceArticle.getReleaseDate());
        verify(articleRepository, times(1)).createArticle(article);
    }

    @Test
    public void shouldDeleteAnArticleById() {
        Mockito.when(articleRepository.removeArticle(isA(Long.class))).thenReturn(true);
        Assert.assertTrue(articleService.removeArticle(article.getId()));
        verify(articleRepository, times(1)).removeArticle(article.getId());
    }

    @Test
    public void shouldUpdateAnArticle() {
        Mockito.when(articleRepository.editArticle(isA(Article.class))).thenReturn(article);
        article.setHeader("New Header");
        Article updatedArticle = articleService.editArticle(article);
        Assert.assertNotNull(updatedArticle);
        Assert.assertEquals(article.getHeader(), updatedArticle.getHeader());
        verify(articleRepository, times(1)).editArticle(article);
    }

    @Test
    public void shouldGetAnArticleById() {
        Mockito.when(articleRepository.getArticleById(isA(Long.class))).thenReturn(article);
        Assert.assertNotNull(articleService.getArticleById(article.getId()));
        Assert.assertEquals(10L, article.getId());
    }

    @Test
    public void shouldGetAllArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        Mockito.when(articleRepository.getAllArticles()).thenReturn(articles);
        Assert.assertNotNull(articleService.getAllArticles());
        verify(articleRepository, times(1)).getAllArticles();
    }
}