package com.ok.authorization.controller;

import com.ok.authorization.config.AppConfig;
import com.ok.authorization.model.*;
import com.ok.authorization.service.ArticleService;
import com.ok.authorization.service.CommentService;
import com.ok.authorization.validation.ArticleValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
public class ArticleControllerTest {

    private List<Article> articles = new ArrayList<>();
    private Article article;
    private Comment comment;
    private MockMvc mockMvc;
    private User user;

    @Mock
    private ArticleService articleService;

    @Mock
    private CommentService commentService;

    @Mock
    private ArticleValidator articleValidator;

    @InjectMocks
    private ArticleController articleController;

    @Before
    public void init() {
        articles.add(new Article());
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
        article = new Article();
        comment = new Comment();
        user = new UserBuilder()
                .withUsername("TUsernameT")
                .build();
    }

    @Test
    public void shouldGetAllArticlesAndReturnArticlesPage() throws Exception {
        when(articleService.getAllArticles()).thenReturn(articles);
        mockMvc.perform(get("/user/articles"))
                .andDo(print())
                .andExpect(forwardedUrl("user/articles"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddArticlesAndReturnArticlesPage() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/user/articles"));
        result.andExpect(MockMvcResultMatchers.view().name("user/articles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article", "listArticles"));
    }

    @Test
    public void shouldAddArticlesAndReturnArticlesPageAgain() throws Exception {
        MockHttpServletRequestBuilder builder = post("/admin/articles/save")
                .flashAttr("article", article);
        when(articleService.createArticle(isA(Article.class))).thenReturn(new Article());
        mockMvc.perform(builder).andExpect(status().is3xxRedirection());
        verify(articleService, times(1)).createArticle(article);
    }

    @Test
    public void shouldUpdateArticlesAndReturnArticlesPageAgain() throws Exception {
        article.setId(10L);
        MockHttpServletRequestBuilder builder = post("/admin/articles/save")
                .flashAttr("article", article);
        when(articleService.editArticle(isA(Article.class))).thenReturn(new Article());
        mockMvc.perform(builder).andExpect(status().is3xxRedirection());
        verify(articleService, times(1)).editArticle(article);
    }

    @Test
    public void shouldReturnArticleAddingForm() throws Exception {
        mockMvc.perform(get("/admin/add"))
                .andDo(print())
                .andExpect(forwardedUrl("admin/addForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRemoveArticleAndReturnArticlesPage() throws Exception {
        MockHttpServletRequestBuilder builder = post("/admin/remove/{id}", anyLong());
        mockMvc.perform(builder).andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldReturnArticleUpdatingForm() throws Exception {
        when(articleService.getArticleById(isA(Long.TYPE))).thenReturn(new Article());
        MockHttpServletRequestBuilder builder = post("/admin/edit/{id}", anyLong());
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(forwardedUrl("admin/addForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article"))
                .andExpect(status().isOk());
        verify(articleService, times(1)).getArticleById(anyLong());
    }

    @Test
    public void shouldReturnArticleInfoPage() throws Exception {
        article.setId(10L);
        MockHttpServletRequestBuilder builder = get("/user/articleInfo/{id}", article.getId())
                .principal(() -> "admin0101")
                .flashAttr("comment", new Comment());
        when(articleService.getArticleById(anyLong())).thenReturn(article);
        when(commentService.getAllComments(isA(Article.class)))
                .thenReturn(new ArrayList<>(Collections.singletonList(comment)));
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(forwardedUrl("user/articleInfo"))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("article", "listComments", "comment", "username"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteAllSelectedArticlesAndReturnArticlesPage() throws Exception {
        MockHttpServletRequestBuilder builder = post("/admin/remove")
                .param("articleId", "10", "5", "55");
        when(articleService.removeArticle(anyLong())).thenReturn(true);
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(articleService, times(3)).removeArticle(anyLong());
    }

    @Test
    public void shouldAddCommentAndReturnArticleInfoPage() throws Exception {
        article.setId(10L);
        MockHttpServletRequestBuilder builder = post("/user/articleInfo/{id}", article.getId())
                .principal(() -> "admin0101")
                .flashAttr("comment", new Comment());
        when(articleService.getArticleById(anyLong())).thenReturn(article);
        when(commentService.getAllComments(isA(Article.class)))
                .thenReturn(new ArrayList<>(Collections.singletonList(comment)));
        when(commentService.createComment(isA(Comment.class), anyLong())).thenReturn(new Comment());
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(forwardedUrl("user/articleInfo"))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("article", "listComments", "comment", "username"))
                .andExpect(status().isOk());
        verify(commentService, times(1)).createComment(isA(Comment.class), anyLong());
    }
}