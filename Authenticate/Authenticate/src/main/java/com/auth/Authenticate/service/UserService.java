package com.auth.Authenticate.service;

/**
 * This class serves as a bridge between the MainController and the UserRepository
 */

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

    /**
     * Checks if a user is attempting registration with an
     * already existing email
     *
     * @param email the email in question (does this email already exist?)
     * @return true if email already exists (user exists), false otherwise
     */
    public boolean checkEmailExists(String email) {
        UserEntity user = repo.findByEmail(email);
        if (user == null) { // if user is null, email does not exist in user table
            return false;
        }
        return true; // email already exists
    }

    /**
     * Save a new user
     *
     * @param userData user attempting registration
     * @return the new user account
     */
    public UserEntity saveUser(final UserDto userData) {
        UserEntity userModel = addUserData(userData);
        return repo.save(userModel);
    }

    /**
     * Helper method for saveUser() function. Creates a UserEntity from
     * the UserDTO.
     *
     * @param userData the user being created (DTO)
     * @return new UserEntity
     */
    public UserEntity addUserData(final UserDto userData) {
        // set user fields
        UserEntity user = new UserEntity();
        user.setfName(userData.getfName());
        user.setlName(userData.getlName());
        user.setEmail(userData.getEmail());
        // encode password using BCrypt hashing algorithm
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        repo.save(user);
        return user;
    }

    /**
     * Select all current users from users table
     *
     * @return list of all current users
     */
    public List<UserEntity> listAll() {
        return repo.findAll();
    }

    /**
     * Save user account
     *
     * @param user user being saved
     */
    public void save(UserEntity user) {
        repo.save(user);
    }

    /**
     * Select user by unique id
     *
     * @param id users unique id
     * @return user entity
     */
    public UserEntity get(Integer id) {
        return repo.findById(id).get();
    }

    /**
     * Select user by unique email
     *
     * @param email key used to select user
     * @return user whom the unique email belongs to
     */
    public UserEntity getByEmail(String email){
        return repo.findByEmail(email);
    }

    /**
     * Select user by email/password combination.
     * Check if password and email match any existing users
     *
     * @param email email
     * @param password password
     * @return user if match, null otherwise
     */
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

    /**
     * Delete user by unique id
     *
     * @param id users unique id
     */
    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
