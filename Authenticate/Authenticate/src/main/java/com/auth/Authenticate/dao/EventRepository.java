package com.auth.Authenticate.dao;

/**
 * The Data Object Layer (DAO) handles the database queries for the EVENTS table
 */

import com.auth.Authenticate.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}
