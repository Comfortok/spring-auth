package com.ok.authorization.repository;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;

public interface RoleRepository {
    boolean addRole(Role role);
    boolean removeRole(Role role);
}