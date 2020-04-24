package com.ok.authorization.service;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
import com.ok.authorization.model.User;

import java.util.Date;
import java.util.Set;

public class ArticleBuilder extends Article {
    private long id;
    private String header;
    private String text;
    private Date releaseDate;
    private User user;
    private Set<Comment> comments;

    public ArticleBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ArticleBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public ArticleBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ArticleBuilder withReleaseDate(Date date) {
        this.releaseDate = date;
        return this;
    }

    public ArticleBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public ArticleBuilder withComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Article build() {
        return new Article(id, header, text, releaseDate, user, comments);
    }
}