package com.wakeb.jobsapplication.service;

import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(String id) {
        return jobRepository.findById(Long.valueOf(id)).orElseThrow();
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(String id) {
        jobRepository.deleteById(Long.valueOf(id));
    }
}
