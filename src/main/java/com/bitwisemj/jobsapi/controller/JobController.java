package com.bitwisemj.jobsapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitwisemj.jobsapi.dto.JobRequestDTO;
import com.bitwisemj.jobsapi.dto.JobResponseDTO;
import com.bitwisemj.jobsapi.service.CreateJobService;
import com.bitwisemj.jobsapi.service.GetJobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    
    private final CreateJobService createJobService;
    private final GetJobService getJobService;

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody final JobRequestDTO jobDTO) {

        createJobService.createJob(jobDTO);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .build();
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getJobs() {

        return ResponseEntity.ok(getJobService.getJobs());
    }

    @GetMapping("by-experience")
    public ResponseEntity<List<JobResponseDTO>> getJobsByExperience(
        @RequestParam("minXp") final Integer minXp,
        @RequestParam("maxXp") final Integer maxXp) {

        return ResponseEntity.ok(getJobService.getJobsByExperience(minXp, maxXp));
    }
}
