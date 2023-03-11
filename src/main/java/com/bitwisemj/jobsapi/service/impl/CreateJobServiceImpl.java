package com.bitwisemj.jobsapi.service.impl;

import org.springframework.stereotype.Service;

import com.bitwisemj.jobsapi.dto.JobRequestDTO;
import com.bitwisemj.jobsapi.entity.Job;
import com.bitwisemj.jobsapi.mapper.JobMapper;
import com.bitwisemj.jobsapi.repository.JobRepository;
import com.bitwisemj.jobsapi.service.CreateJobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CreateJobServiceImpl implements CreateJobService {

    private final JobRepository repository;
    private final JobMapper mapper;
    
    @Override
    public void createJob(final JobRequestDTO jobDTO) {

        log.info("A new job will be created with title {} and description {}",
            jobDTO::getTitle, jobDTO::getDescription);

        final Job job = mapper.toJob(jobDTO);
        repository.save(job);
        log.info("Job created successfully with id {}", job::getId);
    }
    
}
