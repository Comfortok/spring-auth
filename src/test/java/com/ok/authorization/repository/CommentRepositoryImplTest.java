package com.ok.authorization.repository;

import com.ok.authorization.config.AppConfig;
import com.ok.authorization.model.*;
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
public class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;
    private Comment comment;
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
        comment = new CommentBuilder()
                .withText("CommentText")
                .withUser(user)
                .build();
    }

    @Test
    public void shouldCreateComment() {
        articleRepository.createArticle(article);
        List<Article> articles = articleRepository.getAllArticles();
        Article addedArticle = articles.stream()
                .filter(a -> a.getHeader().equals(article.getHeader())).findAny().orElse(null);

        commentRepository.createComment(comment, addedArticle.getId());
        List<Comment> comments = commentRepository.getAllComments(addedArticle);
        if (comments.size() > 0) {
            Assert.assertEquals(comments.get(0).getText(), comment.getText());
        }
    }
}
