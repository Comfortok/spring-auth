package com.ok.authorization.repository;

import com.ok.authorization.model.Role;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {
    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        String username = role.getUser().getUsername();
        String userRole = role.getRole();
        logger.warn("Adding " + username + " and its " + userRole + " role to database.");
        try {
            entityManager.createNativeQuery("INSERT INTO user_roles (USERNAME, ROLE) VALUES(?,?)")
                    .setParameter(1, username)
                    .setParameter(2, userRole)
                    .executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while add a username and its role to database. ", e);
        }
        logger.info(username + " with " + userRole + " has successfully added to database.");
    }

    @Override
    public void removeRole(Role role) {
        logger.warn("Deleting a role for user from database.");
        try {
            sessionFactory.getCurrentSession().delete(role);
        } catch (HibernateException e) {
            logger.error("An exception has happened while deleting a role for user from the database. ", e);
        }
        logger.info("A role for user has successfully deleted from database.");
    }
}