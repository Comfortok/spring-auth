package com.ok.authorization.service;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;

public class RoleBuilder extends Role {
    private long id;
    private String role;
    private User user;

    public RoleBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RoleBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public RoleBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Role build() {
        return new Role(id, role, user);
    }
}