package com.wakeb.jobsapplication.repository;

import com.wakeb.jobsapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.email = :email and u.deletedAt is null")
    Optional<User> findByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.email = :email")
    void deleteByEmail(@Param("email") String email);
}