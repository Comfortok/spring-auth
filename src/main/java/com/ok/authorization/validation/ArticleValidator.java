package com.ok.authorization.validation;

import com.ok.authorization.model.Article;
import com.ok.authorization.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ArticleValidator implements Validator {
    private static final Logger logger = LoggerFactory.getLogger(ArticleValidator.class);

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
                if (!(article.getId() > 0)) {
                    logger.error("An article with such header '" + article.getHeader()
                            + "' is already exists in the database.");
                    errors.rejectValue("header", "validation.header.duplicate");
                }
            }
        });
    }
}