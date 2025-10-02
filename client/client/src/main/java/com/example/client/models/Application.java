package com.example.client.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Application {
    private Long id;
    private User user;
    private Job job;

    @JsonProperty("resumeUrl")
    private String resumeUrl;

    @JsonProperty("appliedAt")
    private Instant appliedAt;
    public Application() {}

    public Application(Long id, User user, Job job, String resumeUrl, Instant appliedAt) {
        this.id = id;
        this.user = user;
        this.job = job;
        this.resumeUrl = resumeUrl;
        this.appliedAt = appliedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }

    public Instant getAppliedAt() { return appliedAt; }
    public void setAppliedAt(Instant appliedAt) { this.appliedAt = appliedAt; }


}
