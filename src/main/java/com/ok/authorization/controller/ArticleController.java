package com.ok.authorization.controller;

import com.ok.authorization.exception.ArticleNotFoundException;
import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
import com.ok.authorization.model.User;
import com.ok.authorization.service.ArticleService;
import com.ok.authorization.service.CommentService;
import com.ok.authorization.validation.ArticleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private static final long HAS_NO_ID = 0;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleValidator articleValidator;

    @RequestMapping(value = "/user/articles", method = RequestMethod.GET)
    public String listArticles(Model model) {
        logger.info("Getting 'user/articles' page.");
        model.addAttribute("article", new Article());
        model.addAttribute("listArticles", this.articleService.getAllArticles());
        return "user/articles";
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "releaseDate",
                new CustomDateEditor(dateFormat, true));
    }

    @PostMapping(value = "/admin/articles/save")
    public String addArticle(@Valid @ModelAttribute("article") Article article,
                             BindingResult bindingResult, Errors errors) {
        logger.info("Getting 'admin/articles/save' page.");
        logger.info("Validating an article.");
        articleValidator.validate(article, bindingResult);
        if (bindingResult.hasErrors() || errors.hasErrors()) {
            return "admin/addForm";
        }
        logger.info("Validation successfully completed. No errors found.");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            User user = new User();
            user.setUsername(userDetail.getUsername());
            article.setUser(user);
        }
        if (article.getId() == HAS_NO_ID) {
            this.articleService.createArticle(article);
        } else {
            this.articleService.editArticle(article);
        }
        return "redirect:/user/articles";
    }

    @RequestMapping("/admin/add")
    public String newArticleForm(@ModelAttribute("article") Article article, Model model) {
        logger.info("Getting 'admin/add' page.");
        model.addAttribute(article);
        return "admin/addForm";
    }

    @RequestMapping(value = "/admin/remove/{id}", method = RequestMethod.POST)
    public String removeArticle(@PathVariable("id") long id) {
        logger.info("Getting 'admin/remove' page.");
        this.articleService.removeArticle(id);
        return "redirect:/user/articles";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String editArticle(@PathVariable("id") long id, Model model) {
        logger.info("Getting 'admin/edit' page.");
        model.addAttribute("article", this.articleService.getArticleById(id));
        return "admin/addForm";
    }

    @RequestMapping(value = "user/articleInfo/{id}", method = RequestMethod.GET)
    public String articleInfo(@PathVariable("id") long id, Model model,
                              @ModelAttribute("comment") Comment comment) {
        logger.info("Getting 'user/articleInfo' page.");
        Article article;
        try {
            article = this.articleService.getArticleById(id);
        } catch (RuntimeException e) {
            logger.error("ArticleNotFoundException has happened. ", e);
            throw new ArticleNotFoundException(id);
        }
        model.addAttribute("article", article);
        model.addAttribute("listComments", this.commentService.getAllComments(article));
        model.addAttribute("comment", comment);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addAttribute("username", userDetail.getUsername());
        }
        return "user/articleInfo";
    }

    @RequestMapping(value = "/admin/remove", method = RequestMethod.POST)
    public String remove(HttpServletRequest request, ModelMap modelMap) {
        logger.info("Getting an articleId in remove method.");
        String[] articleId = request.getParameterValues("articleId");
        if (articleId != null) {
            logger.info("articleId is correct.");
            for (String id : articleId) {
                this.articleService.removeArticle(Integer.parseInt(id));
            }
        } else {
            logger.error("articleId is null.");
            modelMap.put("error", "error in remove method");
        }
        return "redirect:/user/articles";
    }

    @PostMapping(value = "user/articleInfo/{id}")
    public String addComment(@Valid @ModelAttribute("comment") Comment comment, Errors errors,
                             @PathVariable("id") long id, Model model) {
        logger.info("Comment validation started.");
        if (errors.hasErrors()) {
            logger.error("There are some errors while adding a comment.");
            return articleInfo(id, model, comment);
        }
        logger.info("Comment validation ended. Everything is correct.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) authentication.getPrincipal();
            User user = new User();
            user.setUsername(userDetail.getUsername());
            comment.setUser(user);
        }
        this.commentService.createComment(comment, id);
        comment.setText("");
        return articleInfo(id, model, comment);
    }
}