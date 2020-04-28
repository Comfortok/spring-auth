package com.ok.authorization.repository;

import com.ok.authorization.model.User;

import java.util.List;

public interface UserRepository {
    User findByUserName(String username);
    User createUser(User user);
    List<User> getAllUsers();
    boolean enableUser(String username);
    boolean disableUser(String username);
}