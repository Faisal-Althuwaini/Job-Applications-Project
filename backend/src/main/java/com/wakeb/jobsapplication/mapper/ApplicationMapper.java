package com.wakeb.jobsapplication.mapper;

import com.wakeb.jobsapplication.dto.ApplicationResponseDTO;
import com.wakeb.jobsapplication.entity.Application;
import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.entity.User;

public class ApplicationMapper {

    public static Application toEntity(ApplicationResponseDTO dto, User user, Job job) {
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setResumeUrl(dto.getResumeUrl());
        return application;
    }

    public static ApplicationResponseDTO toDTO(Application application) {
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setUserId(application.getUser().getId());
        dto.setJobId(application.getJob().getId());
        dto.setResumeUrl(application.getResumeUrl());
        return dto;
    }
}
