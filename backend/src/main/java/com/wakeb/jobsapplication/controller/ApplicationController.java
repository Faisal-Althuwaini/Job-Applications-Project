package com.wakeb.jobsapplication.controller;

import com.wakeb.jobsapplication.dto.AllApplicationResponseDTO;
import com.wakeb.jobsapplication.dto.ApplicationRequestDTO;
import com.wakeb.jobsapplication.dto.ApplicationResponseDTO;
import com.wakeb.jobsapplication.service.ApplicationService;
import com.wakeb.jobsapplication.service.S3Service;
import com.wakeb.jobsapplication.utils.Authentcation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping("applications")
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    S3Service s3Service;


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
    public ResponseEntity<ApplicationResponseDTO> createApplication(
            @RequestPart("applicationData") ApplicationRequestDTO applicationData,
            @RequestPart("resumeFile") MultipartFile resumeFile) {

        String email = Authentcation.getAuthenticatedEmail();
        System.out.println("applicationData: " + applicationData);
        System.out.println("File name: " + resumeFile.getOriginalFilename());

        String resumeUrl = s3Service.uploadFile(resumeFile);


        ApplicationResponseDTO responseDTO = new ApplicationResponseDTO();
        responseDTO.setJobId(applicationData.getJobId());
        responseDTO.setResumeUrl(resumeUrl);

        return ResponseEntity.ok(applicationService.addApplication(responseDTO, email));
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
