package com.ok.authorization.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long id) {
        super("Article not found: id + " + id);
    }
}