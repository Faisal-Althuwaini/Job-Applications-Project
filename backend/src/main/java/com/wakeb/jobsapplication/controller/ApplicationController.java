package com.wakeb.jobsapplication.controller;

import com.wakeb.jobsapplication.dto.AllApplicationResponseDTO;
import com.wakeb.jobsapplication.dto.ApplicationResponseDTO;
import com.wakeb.jobsapplication.entity.Application;
import com.wakeb.jobsapplication.service.ApplicationService;
import com.wakeb.jobsapplication.utils.Authentcation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("applications")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AllApplicationResponseDTO>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<AllApplicationResponseDTO> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody ApplicationResponseDTO application) {
        String email = Authentcation.getAuthenticatedEmail();
        return ResponseEntity.ok(applicationService.addApplication(application, email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> hasAppliedToJob(@RequestParam Long jobId) {
        String email = Authentcation.getAuthenticatedEmail();
        boolean hasApplied = applicationService.hasUserAppliedToJob(email, jobId);
        return ResponseEntity.ok(hasApplied);
    }


}
