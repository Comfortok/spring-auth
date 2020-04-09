package com.ok.authorization.service;

import com.ok.authorization.model.User;

import java.util.List;

public interface UserService {
    User findByUserName(String username);
    void createUser(User user);
    List<User> getAllUsers();
    void enableUser(String username);
    void disableUser(String username);
}