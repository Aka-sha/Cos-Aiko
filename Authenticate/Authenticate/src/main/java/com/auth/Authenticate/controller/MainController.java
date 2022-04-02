package com.auth.Authenticate.controller;

/**
 * This is a controller class with endpoints for the USERS table in our MySQL database.
 * Any CRUD operation performed to this table by the CosAiko application will be routed
 * via this MainController class
 */


import java.io.IOException;
import java.util.*;

import com.auth.Authenticate.data.UserDto;
import com.auth.Authenticate.entity.EventEntity;
import com.auth.Authenticate.entity.UserEntity;
import com.auth.Authenticate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class MainController {
    // REST Controller
    // Exposes RESTful API's for CRUD operations

    @Autowired
    private UserService service;

    // CRUD Operations

    // READ (GET) \\

    /**
     * This function returns a list of all current users
     *
     * @return list of all current users
     */
    @GetMapping("/users")
    public List<UserDto> list() { // All users

        List<UserEntity> allUsers =  service.listAll();
        List<UserDto> users = new ArrayList<>();

        for(UserEntity userEntity: allUsers){
            UserDto userDto = new UserDto();
            userDto.setfName(userEntity.getfName());
            userDto.setlName(userEntity.getlName());
            userDto.setEmail(userEntity.getEmail());
            userDto.setPassword(userEntity.getPassword());

            users.add(userDto);
        }

        return users;
    }

    /**
     * This function gets a user from DB by their unique email
     *
     * @param email users email
     * @return user and httpstatus code (OK = 200 or NOT_FOUND = 404)
     */
    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) { // get user by email
        try {
            // check if users email exists
            UserEntity user = service.getByEmail(email);
            if (user == null) {
                throw new NoSuchElementException();
            }
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function selects a user by their unique id
     *
     * @param id users id
     * @return user and httpstatus code (OK = 200 or NOT_FOUND = 404)
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Integer id) { // get user by ID
        try {
            UserEntity user = service.get(id);

            UserDto userDto = new UserDto();
            userDto.setfName(user.getfName());
            userDto.setlName(user.getlName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());

            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function checks whether the given email/password combination
     * matches any existing user
     *
     * @param email attempted login email
     * @param password attempted login password
     * @return
     */
    @GetMapping("/users/{email}/{password}") // get user by email AND password - for authentication
    public ResponseEntity<UserDto> getUserCredentials(@PathVariable String email, @PathVariable String password) {
        try {
            UserEntity user = service.getCredentials(email, password);
            if (user == null) { // user email-password combo doesn't match any records
                throw new NoSuchElementException();
            }

            UserDto userDto = new UserDto();
            userDto.setfName(user.getfName());
            userDto.setlName(user.getlName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());

            // return true and status code (200)
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // return true and failure status code
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    // CREATE (POST) \\

    /**
     * This function creates a new user account
     *
     * @param userData a object containing user information (first name, last name, email, password)
     *                 set by the user when creating an account
     * @return user and http status code (OK = 200 or BAD_REQUEST = 400)
     */
    @PostMapping("/users/register") // create new user account
    public ResponseEntity<UserEntity> register(@RequestBody final UserDto userData) {
        if (service.checkEmailExists(userData.getEmail())) { // email already exists
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else { // email does NOT exist - create new user
            UserEntity userEntity = service.saveUser(userData);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
    }

    // UPDATE (PUT) \\

    /**
     * This function updates the users table by unique id
     *
     * @param user user to update
     * @param id id of user to update
     * @return user and httpstatus code (OK = 200 or NOT_FOUND = 404)
     */
    @PutMapping("/users/{id}") // update user by ID
    public ResponseEntity<?> update(@RequestBody UserEntity user, @PathVariable Integer id) {
        try {
            UserEntity existUser = service.get(id);
            service.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function updates the users profile image
     *
     * @param img the updated user profile image
     * @param email the unique email of the user
     * @return user and httpstatus code (OK = 200 or NOT_FOUND = 404)
     */
    @PutMapping("users/updateProfileImage/{email}") // update user profile image
    public ResponseEntity<UserEntity> updateProfileImage(@RequestPart(name = "img")MultipartFile img, @PathVariable String email){
        UserEntity user = service.getByEmail(email); // get user information

        if(user == null){ // check if email is valid (exists)
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }

        try{
            // get bytes from image for storage in DB
            byte[] imgBytes = img.getBytes();
            // set the new user image
            user.setImage(imgBytes);
            // save updated information for user
            service.save(user);
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        }catch(IOException e){
            // unable to handle file
            return new ResponseEntity<UserEntity>(HttpStatus.CONFLICT);
        }
    }

    /**
     *
     * @param userId
     * @param event
     * @return
     */
    @PutMapping("/users/addEvent/{userId}")
    public ResponseEntity<UserEntity> addEvent(@PathVariable Integer userId, @RequestBody EventEntity event){
        // select the user by their id
        UserEntity user = service.get(userId);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // add event to user_events
        user.getEvents().add(event);
        // save the user after performing updates
        service.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // DELETE (DELETE) \\

    /**
     * This function deletes from the users table by id
     *
     * @param id users id
     */
    @DeleteMapping("/users/{id}") // delete user by ID
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
