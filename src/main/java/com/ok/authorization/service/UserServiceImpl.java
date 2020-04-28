package com.ok.authorization.service;

import com.ok.authorization.model.User;
import com.ok.authorization.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public User createUser(User user) {
        logger.warn("Password encoding for user " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Password for user " + user.getUsername() + " has successfully encoded.");
        return userRepository.createUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public boolean enableUser(String username) {
        return userRepository.enableUser(username);
    }

    @Override
    @Transactional
    public boolean disableUser(String username) {
        return userRepository.disableUser(username);
    }
}