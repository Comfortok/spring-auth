package com.ok.authorization.repository;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.createNativeQuery("INSERT INTO user_roles (USERNAME, ROLE) VALUES(?,?)")
                .setParameter(1, role.getUser().getUsername())
                .setParameter(2, role.getRole())
                .executeUpdate();
    }

    @Override
    public void updateRole(String username) {
        Role role = sessionFactory.getCurrentSession().load(Role.class, username);
        if (role != null) {
            sessionFactory.getCurrentSession().saveOrUpdate(role);
        }
    }
}
