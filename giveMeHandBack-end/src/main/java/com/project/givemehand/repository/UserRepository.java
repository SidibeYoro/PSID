package com.project.givemehand.repository;

import com.project.givemehand.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Le repository de User, elle herite de JPA
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id );

}
