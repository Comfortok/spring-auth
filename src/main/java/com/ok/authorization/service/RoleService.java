package com.ok.authorization.service;

import com.ok.authorization.model.Role;

public interface RoleService {
    void addRole(Role role);
    void updateRole(String username);
}
