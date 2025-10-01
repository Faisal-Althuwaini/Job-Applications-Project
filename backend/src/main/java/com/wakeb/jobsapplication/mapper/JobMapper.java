package com.wakeb.jobsapplication.mapper;

import com.wakeb.jobsapplication.dto.ApplicationResponseDTO;
import com.wakeb.jobsapplication.dto.JobDTO;
import com.wakeb.jobsapplication.entity.Application;
import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.entity.User;

public class JobMapper {

    public static Job toEntity(JobDTO dto) {
        Job job = new Job();
//        job.setId(dto.getId());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setDepartment(dto.getDepartment());
        job.setPostedAt(dto.getPostedAt());
        return job;
    }

    public static JobDTO toDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setDepartment(job.getDepartment());
        dto.setPostedAt(job.getPostedAt());
        return dto;
    }

}
