package com.ok.authorization.repository;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;

public interface RoleRepository {
    void addRole(Role role);
    void updateRole(String username);
}