package com.ok.authorization.service;

import com.ok.authorization.model.Role;

public interface RoleService {
    boolean addRole(Role role);
    boolean removeRole(Role role);
}
