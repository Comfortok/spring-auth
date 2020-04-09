package com.ok.authorization.service;

import com.ok.authorization.model.User;
import com.ok.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        System.out.println("create user in UserService");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("user.setRoles... ");
        userRepository.createUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public void enableUser(String username) {
        userRepository.enableUser(username);
    }

    @Override
    @Transactional
    public void disableUser(String username) {
        userRepository.disableUser(username);
    }
}