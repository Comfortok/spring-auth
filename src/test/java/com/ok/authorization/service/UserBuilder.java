package com.ok.authorization.service;

import com.ok.authorization.model.Comment;
import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserBuilder extends User {
    private String username;
    private String password;
    private boolean enabled;
    private Set<Role> roles = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder withRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserBuilder withComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public User build() {
        return new User(username, password, enabled, roles, comments);
    }
}