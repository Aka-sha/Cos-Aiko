package com.auth.Authenticate.dao;

/**
 * The Data Object Layer (DAO) handles the database queries for the USERS table
 */

import com.auth.Authenticate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // Generates Implementation code for CRUD operations
    // find user by email AND password combination
    UserEntity findByEmailAndPassword(String email, String password);
    // findByEmail
    UserEntity findByEmail(String email);
}
