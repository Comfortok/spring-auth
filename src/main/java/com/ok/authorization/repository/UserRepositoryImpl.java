package com.ok.authorization.repository;

import com.ok.authorization.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
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
//        sessionFactory.getCurrentSession().save(user);
        System.out.println("User dao. A user was created with email " + user.getUsername());
    }
}