package com.ok.authorization.exception;

import com.ok.authorization.exception.ArticleNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @ExceptionHandler(ArticleNotFoundException.class)
    public ModelAndView handle() {
        ModelAndView model = new ModelAndView();
        model.setViewName("error/404");
        model.addObject("message", "true");
        return model;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundError() {
        logger.error("NoHandlerFoundException has happened. Redirecting to 404 page.");
        ModelAndView model = new ModelAndView();
        model.setViewName("error/404");
        model.addObject("message", "404");
        return model;
    }
}