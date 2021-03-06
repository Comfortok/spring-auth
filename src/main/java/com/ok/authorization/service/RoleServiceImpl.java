package com.ok.authorization.service;

import com.ok.authorization.model.Role;
import com.ok.authorization.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public boolean addRole(Role role) {
        return roleRepository.addRole(role);
    }

    @Override
    @Transactional
    public boolean removeRole(Role role) {
        return roleRepository.removeRole(role);
    }
}