package com.auth.Authenticate.dao;

/**
 * The Data Object Layer (DAO) handles the database queries for the CONVENTION (cons) table
 */

import com.auth.Authenticate.entity.ConventionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConventionRepository extends JpaRepository<ConventionEntity, Integer> {
}
