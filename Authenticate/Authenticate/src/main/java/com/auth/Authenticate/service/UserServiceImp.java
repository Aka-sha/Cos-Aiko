package com.auth.Authenticate.service;

import com.auth.Authenticate.dao.UserRepository;
import com.auth.Authenticate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//@Service
public class UserServiceImp{
    private final EntityManager entityManager;

    @Autowired
    public UserServiceImp(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Autowired
    private UserRepository userRepository;


    //@Override
    public User getUser(User userDetails) {
        TypedQuery<User> typedQuery = entityManager.createQuery(
                "FROM User WHERE password = :password AND email = :email", User.class);
        try {
            User user = typedQuery.setParameter("password", userDetails.getPassword()).setParameter("email", userDetails.getEmail()).getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    /*
    @Override
    public User insertIntoDatabase(User user) {
        return userRepository.save(user);
    }
    */
}
