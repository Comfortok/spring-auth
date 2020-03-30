package com.ok.authorization.validation;

import com.ok.authorization.model.Comment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Comment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comment comment = (Comment) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "validation.field.required");
        if (comment.getText().length() < 2 || comment.getText().length() > 500) {
            errors.rejectValue("text", "validation.comment.length");
        }
    }
}