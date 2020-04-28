package com.ok.authorization.model;

public class CommentBuilder {
    private long id;
    private String text;
    private Article article;
    private User user;

    public CommentBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public CommentBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public CommentBuilder withArticle(Article article) {
        this.article = article;
        return this;
    }

    public CommentBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Comment build() {
        return new Comment(id, text, article, user);
    }
}