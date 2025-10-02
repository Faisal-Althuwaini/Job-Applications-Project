package com.wakeb.jobsapplication.controller;

import com.wakeb.jobsapplication.dto.JobDTO;
import com.wakeb.jobsapplication.service.JobService;
import com.wakeb.jobsapplication.utils.Authentcation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@SecurityRequirement(name = "bearerAuth")
public class JobsController {
    @Autowired
    JobService jobService;

    @GetMapping()
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO job) {
        String email = Authentcation.getAuthenticatedEmail();
        return ResponseEntity.ok(jobService.createJob(job, email));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteByJobId(id);
        return ResponseEntity.ok().body("Job deleted successfully");
    }
}
