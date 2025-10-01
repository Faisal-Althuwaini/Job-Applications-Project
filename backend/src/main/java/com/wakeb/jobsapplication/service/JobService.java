package com.wakeb.jobsapplication.service;

import com.wakeb.jobsapplication.dto.JobDTO;
import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.entity.User;
import com.wakeb.jobsapplication.mapper.JobMapper;
import com.wakeb.jobsapplication.repository.JobRepository;
import com.wakeb.jobsapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public List<JobDTO> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();
       return jobs.stream().map(JobMapper::toDTO).toList();

    }

    public JobDTO getJobById(String id) {
       Job job = jobRepository.findById(Long.valueOf(id)).orElseThrow();
       return  JobMapper.toDTO(job);
    }

    public JobDTO createJob(JobDTO job, String email) {

        Job jobEntity = JobMapper.toEntity(job);

        User user = userRepository.findByEmail(email).orElseThrow();
        jobEntity.setPostedBy(user);

        jobRepository.save(jobEntity);
        return job;
    }

    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(String id) {
        jobRepository.deleteById(Long.valueOf(id));
    }
}
