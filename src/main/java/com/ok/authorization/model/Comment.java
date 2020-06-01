package com.ok.authorization.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "comments")
@NamedQuery(name = "getCommentsByArticleId", query = "SELECT e from Comment e where e.article.id = :id")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    @NotEmpty
    @Size(min = 2, max = 500, message = "{validation.comment.length}")
    private String text;

    @ManyToOne(targetEntity = Article.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Comment() {
    }

    public Comment(long id, String text, Article article, User user) {
        this.id = id;
        this.text = text;
        this.article = article;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}