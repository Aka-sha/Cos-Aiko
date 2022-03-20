package com.auth.Authenticate.controller;

import java.io.IOException;
import java.util.*;

import com.auth.Authenticate.data.UserDto;
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
    @GetMapping("/users")
    public List<UserEntity> list() { // All users
        return service.listAll();
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) { // get user by email
        try {
            UserEntity user = service.getByEmail(email);
            if (user == null) {
                throw new NoSuchElementException();
            }
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable Integer id) { // User by ID
        try {
            UserEntity user = service.get(id);
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{email}/{password}") // User by email AND password - for authentication
    public ResponseEntity<UserEntity> getUserCredentials(@PathVariable String email, @PathVariable String password) {
        try {
            UserEntity user = service.getCredentials(email, password);
            if (user == null) { // user email-password combo doesn't match any records
                throw new NoSuchElementException();
            }
            // return true and status code (200)
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // return true and failure status code
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
    }

    // CREATE (POST) \\
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

    // DELETE (DELETE) \\
    @DeleteMapping("/users/{id}") // delete user by ID
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
