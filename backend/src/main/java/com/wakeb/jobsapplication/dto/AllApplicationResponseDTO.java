package com.wakeb.jobsapplication.dto;

import com.wakeb.jobsapplication.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllApplicationResponseDTO {
    private Long id;
    private UserDTO user;
    private JobDTO job;
    private String resumeUrl;
}
