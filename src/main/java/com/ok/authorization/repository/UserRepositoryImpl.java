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

    @SuppressWarnings("unchecked")
    @Override
    public User findByUserName(String username) {
        System.out.println("findBy...");
        return sessionFactory.getCurrentSession().get(User.class, username);
//        System.out.println("UserRepo.findBy");
//        List<User> users = new ArrayList<>();
//        Query query = sessionFactory.getCurrentSession().createQuery("select user from User user where user.username=:username");
//        System.out.println("Query: " + query.list());
//        query.setString("username", username);
//        users = query.list();
//                users = sessionFactory.getCurrentSession()
//                .createQuery("select user from User user where user.username=:username")
//                .setParameter("username", username)
//                .list();

//        if (users.size() > 0) {
//            System.out.println("User found: " + users.get(0));
//            return (User) users.get(0);
//        } else {
//            return null;
//        }
    }
}