package com.ok.authorization.service;

import com.ok.authorization.config.core.SpringMvcInitializer;
import com.ok.authorization.config.core.SpringSecurityInitializer;
import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
import com.ok.authorization.model.CommentBuilder;
import com.ok.authorization.repository.CommentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class})
@WebAppConfiguration
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;
    private Comment comment;

    @InjectMocks
    private CommentService commentService = new CommentServiceImpl();

    @Before
    public void setup() {
        comment = new CommentBuilder()
                .withId(1L)
                .withText("TText")
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateAComment() {
        Mockito.when(commentRepository.createComment(isA(Comment.class), Mockito.anyLong())).thenReturn(comment);
        Comment createdComment = commentService.createComment(new CommentBuilder().build(), 10L);
        Assert.assertNotNull(createdComment);
        Assert.assertEquals(createdComment.getText(), comment.getText());
    }

    @Test
    public void shouldGetAllComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Mockito.when(commentRepository.getAllComments(isA(Article.class))).thenReturn(comments);
        List<Comment> commentList = commentService.getAllComments(new Article());
        Assert.assertNotNull(commentList);
        Assert.assertEquals(commentList.get(0), comment);
    }
}