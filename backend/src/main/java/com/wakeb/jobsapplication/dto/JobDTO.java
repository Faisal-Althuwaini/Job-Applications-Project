package com.wakeb.jobsapplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private Long id;

    private String title;

    private String description;

    private String location;

    private String department;

    private Instant postedAt;

}
