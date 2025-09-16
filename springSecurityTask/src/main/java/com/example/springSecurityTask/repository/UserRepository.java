package com.example.springSecurityTask.repository;

import com.example.springSecurityTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository
 * 
 * Following the same pattern as other repositories in the project.
 * Provides database operations for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by username - used for authentication
     * @param username the username to search for
     * @return Optional containing User if found, empty if not found
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Check if username already exists - useful for user registration
     * @param username the username to check
     * @return true if username exists, false otherwise
     */
    boolean existsByUsername(String username);
}
