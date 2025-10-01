package com.wakeb.jobsapplication.repository;

import com.wakeb.jobsapplication.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
