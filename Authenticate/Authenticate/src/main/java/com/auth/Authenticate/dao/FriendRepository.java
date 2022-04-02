package com.auth.Authenticate.dao;

/**
 * The Data Object Layer (DAO) handles the database queries for the FRIENDS table
 */

import com.auth.Authenticate.entity.Friendship;
import com.auth.Authenticate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friendship, Integer> {
    boolean existsByRequesterIdAndReceiverId(UserEntity requester, UserEntity receiver);

    List<Friendship> findByRequesterId(UserEntity user);
    List<Friendship> findByReceiverId(UserEntity user);
}
