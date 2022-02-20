package com.auth.Authenticate.controller;

import java.util.*;

import com.auth.Authenticate.entity.User;
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
    public List<User> list() { // All users
        return service.listAll();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) { // User by ID
        try {
            User user = service.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("users/{email}/{password}") // User by email AND password - for authentication
    public ResponseEntity<String> getUserCredentials(@PathVariable String email, @PathVariable String password) {
        try {
            User user = service.getCredentials(email, password);
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
    @PostMapping("/users/newUser") // create new user account
    public void add(@RequestBody User user) {
        service.save(user);
    }

    // UPDATE (PUT) \\
    @PutMapping("/users/{id}") // update user by ID
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existUser = service.get(id);
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
