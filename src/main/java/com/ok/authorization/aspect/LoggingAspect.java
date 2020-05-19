package com.ok.authorization.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.ok.authorization.service.ArticleService.getAllArticlesSortedByHeader(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("logBefore() in ArticleService's method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.ok.authorization.service.ArticleService.getAllArticlesSortedByHeader(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("logAfter() in ArticleService's method: " + joinPoint.getSignature().getName());
    }
}