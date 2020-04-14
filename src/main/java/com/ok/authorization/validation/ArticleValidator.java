package com.ok.authorization.validation;

import com.ok.authorization.model.Article;
import com.ok.authorization.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ArticleValidator implements Validator {

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Article.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Article article = (Article) o;
        articleService.getAllArticles().forEach(a -> {
            if (a.getHeader().equalsIgnoreCase(article.getHeader())) {
                if (article.getId() > 0) {
                } else {
                    errors.rejectValue("header", "validation.header.duplicate");
                }
            }
        });
    }
}