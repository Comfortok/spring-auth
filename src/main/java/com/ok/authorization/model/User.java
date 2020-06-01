package com.ok.authorization.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", unique = true,
    nullable = false)
    @NotEmpty(message = "{validation.field.required}")
    @Size(min = 4, max = 32, message = "{validation.username.size}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{validation.username.format}")
    private String username;

    @NotEmpty(message = "{validation.field.required}")
    @Column(name = "password", nullable = false)
    @Size(min = 4, max = 32, message = "{validation.password.size}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{validation.password.format}")
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Role> roles;

    @Transient
    @NotEmpty(message = "{validation.field.required}")
    @Size(min = 4, max = 32, message = "{validation.password.size}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{validation.password.format}")
    private String confirmPassword;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Article> articles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Comment> comments;

    public User() {
    }

    public User(String username, String password, boolean enabled, Set<Role> roles, Set<Comment> comments) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}