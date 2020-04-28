package com.ok.authorization.repository;

import com.ok.authorization.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private static final String INSERT_USER_TO_DB = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES(?,?)";
    private static final String GET_ALL_USERS = "from User";
    private static final String ENABLE_USER_BY_USERNAME = "UPDATE users SET enabled = 1 WHERE username = ?";
    private static final String DISABLE_USER_BY_USERNAME = "UPDATE users SET enabled = 0 WHERE username = ?";

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public User findByUserName(String username) {
        User user = null;
        logger.warn("Finding a user with username " + username);
        try {
            user = sessionFactory.getCurrentSession().get(User.class, username);
        } catch (HibernateException e) {
            logger.error("An exception has happened while finding a user from the database. ", e);
        }
        logger.info("A user with username " + username + " has found.");
        return user;
    }

    @Override
    public User createUser(User user) {
        logger.warn("Adding a user to the database with username " + user.getUsername());
        try {
            entityManager.createNativeQuery(INSERT_USER_TO_DB)
                    .setParameter(1, user.getUsername())
                    .setParameter(2, user.getPassword())
                    .executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while adding a user to the database. ", e);
        }
        logger.info("A user with username " + user.getUsername() + " has been added to the database.");
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        logger.warn("Getting all users from the database.");
        try {
            users = sessionFactory.getCurrentSession().createQuery(GET_ALL_USERS).list();
        } catch (HibernateException e) {
            logger.error("An exception has happened while getting all users from the database. ", e);
        }
        logger.info("All users have been loaded.");
        return users;
    }

    @Override
    public boolean enableUser(String username) {
        logger.warn("Enabling a user with username " + username);
        try {
            entityManager.createNativeQuery(ENABLE_USER_BY_USERNAME)
                    .setParameter(1, username)
                    .executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while enabling a user. ", e);
        }
        logger.info("A user with username " + username + " has successfully enabled.");
        return true;
    }

    @Override
    public boolean disableUser(String username) {
        logger.warn("Disabling a user with username " + username);
        try {
            entityManager.createNativeQuery(DISABLE_USER_BY_USERNAME)
                    .setParameter(1, username)
                    .executeUpdate();
        } catch (Exception e) {
            logger.error("An exception has happened while disabling a user. ", e);
        }
        logger.info("A user with username " + username + " has successfully disabled.");
        return false;
    }
}