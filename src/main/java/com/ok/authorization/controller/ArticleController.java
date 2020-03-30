package com.ok.authorization.controller;

import com.ok.authorization.model.Article;
import com.ok.authorization.model.Comment;
import com.ok.authorization.model.User;
import com.ok.authorization.service.ArticleService;
import com.ok.authorization.service.CommentService;
import com.ok.authorization.validation.ArticleValidator;
import com.ok.authorization.validation.CommentValidator;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleValidator articleValidator;

    @Autowired
    private CommentValidator commentValidator;

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String listArticles(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("listArticles", this.articleService.getAllArticles());
        return "articles";
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "releaseDate", new CustomDateEditor(dateFormat, true));
    }

    @PostMapping(value = "/articles/save")
    public String addArticle(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult) {
        articleValidator.validate(article, bindingResult);

        if (bindingResult.hasErrors()) {
            return "addForm";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            User user = new User();
            user.setUsername(userDetail.getUsername());
            article.setUser(user);
            System.out.println("Logged: " + userDetail);
        }
        if (article.getId() == 0) {
            System.out.println("addArticle.id=0");
            this.articleService.createArticle(article);
        } else {
            System.out.println("addArticle.id!=0");
            this.articleService.editArticle(article);
        }
        return "redirect:/articles";
    }

    @RequestMapping("/add")
    public String newArticleForm(@ModelAttribute("article") Article article, Model model) {
        System.out.println(article.getId() + " -- id");
        model.addAttribute(article);
        return "addForm";
    }

    @RequestMapping(value = "/remove/{id}")
    public String removeArticle(@PathVariable("id") long id) {
        this.articleService.removeArticle(id);
        return "redirect:/articles";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editArticle(@PathVariable("id") long id, Model model) {
        model.addAttribute("article", this.articleService.getArticleById(id));
        return "addForm";
    }

    @RequestMapping(value = "articleInfo/{id}", method = RequestMethod.GET)
    public String articleInfo(@PathVariable("id") long id, Model model, @ModelAttribute("comment") Comment comment) {
        Article article = this.articleService.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("listComments", this.commentService.getAllComments(article));
        model.addAttribute("comment", comment);
        return "articleInfo";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(HttpServletRequest request, ModelMap modelMap) {
        String[] articleId = request.getParameterValues("articleId");
        if (articleId != null) {
            for (String id : articleId) {
                this.articleService.removeArticle(Integer.parseInt(id));
            }
        } else {
            modelMap.put("error", "error in remove method");
        }
        return "redirect:/articles";
    }

    @PostMapping(value = "/articleInfo/{id}")
    public String addComment(@ModelAttribute("comment") Comment comment, BindingResult bindingResult,
                             @PathVariable("id") long id, Model model) {
        commentValidator.validate(comment, bindingResult);

        if (bindingResult.hasErrors()) {
            return articleInfo(id, model, comment);
        }

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