package com.bitwisemj.jobsapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bitwisemj.jobsapi.dto.JobResponseDTO;
import com.bitwisemj.jobsapi.mapper.JobMapper;
import com.bitwisemj.jobsapi.repository.JobRepository;
import com.bitwisemj.jobsapi.service.GetJobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GetJobServiceImpl implements GetJobService {

    private final JobRepository repository;
    private final JobMapper mapper;

    @Override
    public List<JobResponseDTO> getJobs() {
        
        log.info("Getting all available jobs");
        
        return repository.findAll()
        .stream()
        .map(mapper::toJobResponseDTO)
        .collect(Collectors.toList());
    }
    
}
