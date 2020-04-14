package com.ok.authorization.repository;

import com.ok.authorization.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public User findByUserName(String username) {
        System.out.println("findBy...");
        return sessionFactory.getCurrentSession().get(User.class, username);
    }

    @Override
    public void createUser(User user) {
        System.out.println("creating user in dao");
        entityManager.createNativeQuery("INSERT INTO USERS (USERNAME, PASSWORD) VALUES(?,?)")
                .setParameter(1, user.getUsername())
                .setParameter(2, user.getPassword())
                .executeUpdate();
        System.out.println("User dao. A user was created with email " + user.getUsername());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void enableUser(String username) {
        entityManager.createNativeQuery("UPDATE users SET enabled = 1 WHERE username = ?")
                .setParameter(1, username)
                .executeUpdate();
    }

    @Override
    public void disableUser(String username) {
        entityManager.createNativeQuery("UPDATE users SET enabled = 0 WHERE username = ?")
                .setParameter(1, username)
                .executeUpdate();
    }
}