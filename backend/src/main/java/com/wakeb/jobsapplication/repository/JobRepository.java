package com.wakeb.jobsapplication.repository;

import com.wakeb.jobsapplication.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE j.deletedAt IS NULL")
    List<Job> findAll();

    @Query("SELECT j FROM Job j WHERE j.id = :id AND j.deletedAt IS NULL")
    Job findByJobId(@Param("id") long id);

    @Modifying
    @Query("UPDATE Job j SET j.deletedAt = CURRENT_TIMESTAMP WHERE j.id = :id")
    void deleteByJobId(@Param("id") long id);
}
