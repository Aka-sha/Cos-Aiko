package com.auth.Authenticate.service;

import com.auth.Authenticate.dao.UserRepository;
import com.auth.Authenticate.entity.User;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
// import javax.transaction.Transactional;
import java.util.List;

// @Component
@Service
public class UserService {
    // Middle layer between UserRepository and MainController
    // Forwards calls to an implementation of the UserRepository

    @Autowired
    // UserRepository
    private UserRepository repo;

    // list all current users
    public List<User> listAll() {
        return repo.findAll();
    }

    // save user
    public void save(User user) {
        repo.save(user);
    }

    // select user by id
    public User get(Integer id) {
        return repo.findById(id).get();
    }

    // select user by email and password - for authenticating login
    public User getCredentials(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    // delete user by id
    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
