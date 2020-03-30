package com.ok.authorization.service;

import com.ok.authorization.model.Role;
import com.ok.authorization.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.addRole(role);
    }

    @Override
    public void updateRole(String username) {
        roleRepository.updateRole(username);
    }
}