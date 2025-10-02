package com.wakeb.jobsapplication.mapper;

import com.wakeb.jobsapplication.dto.AllApplicationResponseDTO;
import com.wakeb.jobsapplication.dto.ApplicationResponseDTO;
import com.wakeb.jobsapplication.dto.JobDTO;
import com.wakeb.jobsapplication.dto.UserDTO;
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
        dto.setJobId(application.getJob().getId());
        dto.setResumeUrl(application.getResumeUrl());
        return dto;
    }

    public static Application AllResponseToEntity(AllApplicationResponseDTO dto, User user, Job job) {
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setResumeUrl(dto.getResumeUrl());
        return application;
    }

    public static AllApplicationResponseDTO AllResponseToDTO(Application application) {
        AllApplicationResponseDTO dto = new AllApplicationResponseDTO();

        UserDTO convertedUser = UserMapper.toDTO(application.getUser());
        dto.setUser(convertedUser);

        JobDTO jobDTO = JobMapper.toDTO(application.getJob());
        dto.setJob(jobDTO);
        dto.setResumeUrl(application.getResumeUrl());
        dto.setId(application.getId());
        dto.setAppliedAt(application.getAppliedAt());
        return dto;
    }
}
