package com.bitwisemj.jobsapi.service;

import com.bitwisemj.jobsapi.dto.JobRequestDTO;

public interface CreateJobService {
    
    void createJob(final JobRequestDTO jobDTO);
}
