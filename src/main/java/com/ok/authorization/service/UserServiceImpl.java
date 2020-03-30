package com.ok.authorization.service;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;
import com.ok.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void createUser(User user) {
        System.out.println("create user in UserService");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setUser(user);
//        role.setRole("ROLE_ADMIN");
//        roles.add(role);
//        System.out.println(roles);
//        user.setRoles(roles);
        System.out.println("user.setRoles... ");
        userRepository.createUser(user);
    }
}