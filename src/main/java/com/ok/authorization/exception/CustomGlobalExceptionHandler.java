package com.ok.authorization.exception;

import com.ok.authorization.exception.ArticleNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ModelAndView handle() {
        ModelAndView model = new ModelAndView();
        model.setViewName("error/404");
        model.addObject("message", "Article not found");
        return model;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundError()
    {
        System.out.println("NoHandlerFoundException happened");
        ModelAndView model = new ModelAndView();
        model.setViewName("error/404");
        model.addObject("message", "404");
        return model;
    }
}