package com.ok.authorization.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
@NamedQuery(name = "getAnArticleById", query = "SELECT e from Article e where e.id = :id")
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "header")
    @NotEmpty
    @Size(min = 2, max = 100, message = "{validation.header.length}")
    private String header;

    @Column(name = "text")
    @NotEmpty
    @Size(min = 2, max = 2500, message = "{validation.text.length}")
    private String text;

    @Column(name = "release_date")
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "article")
    private Set<Comment> comments;

    public Article() {
    }

    public Article(long id, String header, String text, Date releaseDate, User user, Set<Comment> comments) {
        this.id = id;
        this.header = header;
        this.text = text;
        this.releaseDate = releaseDate;
        this.user = user;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}