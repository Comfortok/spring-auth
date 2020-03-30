package com.ok.authorization.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ARTICLE")
@NamedQuery(name = "getAnArticleById", query = "SELECT e from Article e where e.id = :id")
public class Article {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @ManyToOne//FIX IT
    @JoinColumn(name = "USERNAME")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

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