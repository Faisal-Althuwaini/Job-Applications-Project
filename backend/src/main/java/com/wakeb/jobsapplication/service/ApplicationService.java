package com.wakeb.jobsapplication.service;

import com.wakeb.jobsapplication.entity.Application;
import com.wakeb.jobsapplication.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }


}
