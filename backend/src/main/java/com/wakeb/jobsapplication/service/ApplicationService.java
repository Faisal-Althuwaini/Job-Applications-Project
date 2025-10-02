package com.wakeb.jobsapplication.service;

import com.wakeb.jobsapplication.dto.AllApplicationResponseDTO;
import com.wakeb.jobsapplication.dto.ApplicationResponseDTO;
import com.wakeb.jobsapplication.entity.Application;
import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.entity.User;
import com.wakeb.jobsapplication.mapper.ApplicationMapper;
import com.wakeb.jobsapplication.repository.ApplicationRepository;
import com.wakeb.jobsapplication.repository.JobRepository;
import com.wakeb.jobsapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;


    public List<AllApplicationResponseDTO> getAllApplications() {
        List<Application> application = applicationRepository.findAll();
        return application.stream().map(ApplicationMapper::AllResponseToDTO).toList();
    }

    public ApplicationResponseDTO addApplication(ApplicationResponseDTO application, String email) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Job job = jobRepository.findByJobId(application.getJobId());

        boolean alreadyApplied = applicationRepository.existsByUserIdAndJobId(user.getId(), job.getId());
        if (alreadyApplied) {
            throw new RuntimeException("User has already applied to this job.");
        }

        Application applicationSaved = ApplicationMapper.toEntity(application, user, job);
        Application saved = applicationRepository.save(applicationSaved);
        return ApplicationMapper.toDTO(saved) ;
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public AllApplicationResponseDTO getApplicationById(Long id) {

        Application application = applicationRepository.findById(id).orElseThrow();
        return ApplicationMapper.AllResponseToDTO(application);
    }

    public boolean hasUserAppliedToJob(String userEmail, Long jobId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.existsByUserIdAndJobId(user.getId(), jobId);
    }


}
