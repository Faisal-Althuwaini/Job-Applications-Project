package com.wakeb.jobsapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications", indexes = {
        @Index(name = "idx_app_user_id", columnList = "user_id"),
        @Index(name = "idx_app_job_id", columnList = "job_id"),
        @Index(name = "idx_app_user_job", columnList = "user_id, job_id", unique = true),
        @Index(name = "idx_app_applied_at", columnList = "appliedAt")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String resumeUrl;

    @CreationTimestamp
    private Instant appliedAt;
}
