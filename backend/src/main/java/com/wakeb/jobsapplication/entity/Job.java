package com.wakeb.jobsapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "jobs", indexes = {
        @Index(name = "idx_job_deleted_at", columnList = "deleted_at"),
        @Index(name = "idx_job_posted_at", columnList = "postedAt"),
        @Index(name = "idx_job_posted_by", columnList = "posted_by"),
        @Index(name = "idx_job_deleted_posted", columnList = "deleted_at, postedAt")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;

    private String department;

    @CreationTimestamp
    private Instant postedAt;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}
