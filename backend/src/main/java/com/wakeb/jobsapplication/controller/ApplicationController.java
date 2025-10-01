package com.wakeb.jobsapplication.controller;

import com.wakeb.jobsapplication.entity.Application;
import com.wakeb.jobsapplication.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("applications")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping()
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @GetMapping("{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @PostMapping
    public ResponseEntity<Application> addApplication(@RequestBody Application application) {
        return ResponseEntity.ok(applicationService.addApplication(application));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }


}
