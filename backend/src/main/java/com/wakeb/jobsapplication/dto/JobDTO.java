package com.wakeb.jobsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Instant deletedAt;

}
