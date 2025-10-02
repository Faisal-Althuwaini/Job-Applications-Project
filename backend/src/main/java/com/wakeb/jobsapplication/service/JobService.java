package com.wakeb.jobsapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wakeb.jobsapplication.dto.JobDTO;
import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.entity.User;
import com.wakeb.jobsapplication.mapper.JobMapper;
import com.wakeb.jobsapplication.repository.JobRepository;
import com.wakeb.jobsapplication.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

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

    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findByJobId(id);
        return JobMapper.toDTO(job);
    }

    public JobDTO createJob(JobDTO job, String email) {

        Job jobEntity = JobMapper.toEntity(job);

        User user = userRepository.findByEmail(email).orElseThrow();
        jobEntity.setPostedBy(user);

        Job savedJob = jobRepository.save(jobEntity);
        return JobMapper.toDTO(savedJob);
    }

    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    @Transactional
    public void deleteByJobId(Long id) {
        Job job = jobRepository.findByJobId(id);
        jobRepository.deleteByJobId(job.getId());
    }
}
