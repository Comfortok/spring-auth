package com.ok.authorization.service;

import com.ok.authorization.model.User;

public interface UserService {
    User findByUserName(String username);
    void createUser(User user);
}
