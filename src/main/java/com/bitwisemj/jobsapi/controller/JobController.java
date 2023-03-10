package com.bitwisemj.jobsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    
    @PostMapping
    public ResponseEntity<String> createJob() {

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .build();
    }

    @GetMapping
    public ResponseEntity<String> getJobs() {

        return ResponseEntity.status(HttpStatus.OK)
            .build();
    }
}
