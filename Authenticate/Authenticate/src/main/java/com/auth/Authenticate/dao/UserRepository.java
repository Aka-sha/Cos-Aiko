package com.auth.Authenticate.dao;

import com.auth.Authenticate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Generates Implementation code for CRUD operations
    // find user by email AND password combination
    User findByEmailAndPassword(String email, String password);
}
