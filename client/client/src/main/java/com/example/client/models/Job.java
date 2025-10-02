package com.example.client.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String department;
    
    @JsonProperty("postedAt")
    private Instant postedAt;
    
    @JsonProperty("postedBy")
    private User postedBy;

    public Job() {}

    public Job(Long id, String title, String description, String location, String department, Instant postedAt, User postedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.department = department;
        this.postedAt = postedAt;
        this.postedBy = postedBy;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Instant getPostedAt() { return postedAt; }
    public void setPostedAt(Instant postedAt) { this.postedAt = postedAt; }

    public User getPostedBy() { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }
}
