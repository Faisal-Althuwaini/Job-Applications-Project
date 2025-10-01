package com.wakeb.jobsapplication.controller;

import com.wakeb.jobsapplication.entity.Job;
import com.wakeb.jobsapplication.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    JobService jobService;

    @GetMapping()
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(String.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.saveJob(job));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(String.valueOf(id));
        return ResponseEntity.ok().body("Job deleted successfully");
    }
}
