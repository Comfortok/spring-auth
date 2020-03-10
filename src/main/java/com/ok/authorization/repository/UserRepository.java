package com.ok.authorization.repository;

import com.ok.authorization.model.User;

public interface UserRepository {
    User findByUserName(String username);
}