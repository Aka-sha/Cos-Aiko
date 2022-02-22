package com.auth.Authenticate.service;

import com.auth.Authenticate.data.UserDto;
import com.auth.Authenticate.dao.UserRepository;
// import org.springframework.stereotype.Component;
import com.auth.Authenticate.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
// import javax.transaction.Transactional;
import java.util.List;

// @Component
@Service("userService")
public class UserService {
    // Middle layer between UserRepository and MainController
    // Forwards calls to an implementation of the UserRepository

    @Autowired
    // UserRepository
    private UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean checkEmailExists(String email) {
        UserEntity user = repo.findByEmail(email);
        if (user == null) { // if user is null, email does not exist in user table
            return false;
        }
        return true; // email already exists
    }

    public UserEntity saveUser(final UserDto userData) {
        UserEntity userModel = addUserData(userData);
        return repo.save(userModel);
    }

    public UserEntity addUserData(final UserDto userData) {
        UserEntity user = new UserEntity();
        user.setfName(userData.getfName());
        user.setlName(userData.getlName());
        user.setEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        repo.save(user);
        return user;
    }

    // list all current users
    public List<UserEntity> listAll() {
        return repo.findAll();
    }

    // save user
    public void save(UserEntity user) {
        repo.save(user);
    }

    // select user by id
    public UserEntity get(Integer id) {
        return repo.findById(id).get();
    }

    // select user by email and password - for authenticating login
    public UserEntity getCredentials(String email, String password) {
        UserEntity user = repo.findByEmail(email);

        if (user == null) { // email does not exist
            return null;
        } else {
            // get the hashed password
            String hashPwd = user.getPassword();
            // compare plain-text password to hashed password
            boolean pwdMatch = passwordEncoder.matches(password, hashPwd);

            if (pwdMatch) { // passwords match
                return user;
            } else { // passwords do NOT match
                return null;
            }
        }
    }

    // delete user by id
    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
