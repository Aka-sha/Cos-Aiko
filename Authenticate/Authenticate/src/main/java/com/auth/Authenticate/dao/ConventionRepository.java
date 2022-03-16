package com.auth.Authenticate.dao;

import com.auth.Authenticate.entity.ConventionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConventionRepository extends JpaRepository<ConventionEntity, Integer> {
}
