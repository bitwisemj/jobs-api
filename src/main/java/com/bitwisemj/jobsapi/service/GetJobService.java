package com.bitwisemj.jobsapi.service;

import java.util.List;

import com.bitwisemj.jobsapi.dto.JobResponseDTO;

public interface GetJobService {
    
    List<JobResponseDTO> getJobs();
}
