package com.wakeb.jobsapplication.repository;

import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
