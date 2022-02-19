package com.auth.Authenticate.controller;

import java.util.*;

import com.auth.Authenticate.data.UserDto;
import com.auth.Authenticate.entity.UserEntity;
import com.auth.Authenticate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable Integer id) { // User by ID
        try {
            UserEntity user = service.get(id);
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("users/{email}/{password}") // User by email AND password - for authentication
    public ResponseEntity<String> getUserCredentials(@PathVariable String email, @PathVariable String password) {
        try {
            UserEntity user = service.getCredentials(email, password);
            if (user == null) { // user email-password combo doesn't match any records
                throw new NoSuchElementException();
            }
            // return true and status code (200)
            return new ResponseEntity<String>("true", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // return true and failure status code
            return new ResponseEntity<String>("false", HttpStatus.NOT_FOUND);
        }
    }

    // CREATE (POST) \\
    @PostMapping("/users/register") // create new user account
    //@RequestParam("fName") String fName, @RequestParam("lName") String lName, @RequestParam("email") String email, @RequestParam("password") String password
    public UserEntity register(@RequestBody final UserDto userData) {
//        UserDto userDto = new UserDto();
//        userDto.setfName(fName);
//        userDto.setlName(lName);
//        userDto.setEmail(email);
//        userDto.setPassword(password);
        UserEntity userEntity = service.saveUser(userData);
        return userEntity;
    }

    // UPDATE (PUT) \\
    @PutMapping("/users/{id}") // update user by ID
    public ResponseEntity<?> update(@RequestBody UserEntity user, @PathVariable Integer id) {
        try {
            UserEntity existUser = service.get(id);
            service.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE (DELETE) \\
    @DeleteMapping("/users/{id}") // delete user by ID
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
