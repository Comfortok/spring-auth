package com.ok.authorization.validation;

import com.ok.authorization.model.Article;
import com.ok.authorization.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "header", "validation.field.required");
        if (article.getHeader().length() < 2 || article.getHeader().length() > 50) {
            errors.rejectValue("header", "validation.header.length");
        }

        articleService.getAllArticles().forEach(a -> {
            if (a.getHeader().equalsIgnoreCase(article.getHeader())) {
                if (article.getId() > 0) {
                } else {
                    errors.rejectValue("header", "validation.header.duplicate");
                }
            }
        });

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "releaseDate", "validation.field.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "validation.field.required");
        if (article.getText().length() < 2 || article.getText().length() > 2548) {
            errors.rejectValue("text", "validation.text.length");
        }
    }
}