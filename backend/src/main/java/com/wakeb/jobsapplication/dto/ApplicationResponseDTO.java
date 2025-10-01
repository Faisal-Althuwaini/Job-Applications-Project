package com.wakeb.jobsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {
    private Long userId;

    private Long jobId;

    private String resumeUrl;

}
