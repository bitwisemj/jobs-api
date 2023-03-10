package com.bitwisemj.jobsapi.unit.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.bitwisemj.jobsapi.configuration.JsonUtil;
import com.bitwisemj.jobsapi.configuration.UnitTest;
import com.bitwisemj.jobsapi.dto.JobRequestDTO;
import com.bitwisemj.jobsapi.entity.Job;
import com.bitwisemj.jobsapi.mapper.JobMapper;
import com.bitwisemj.jobsapi.repository.JobRepository;
import com.bitwisemj.jobsapi.service.impl.CreateJobServiceImpl;

@UnitTest
class CreateJobServiceImplTests {
    
    private static final String JOB_REQUEST_JSON_PATH = 
    "src/test/resources/json/job-request.json";
    @Mock
    private JobRepository repository;
    @Mock
    private JobMapper mapper;
    @InjectMocks
    private CreateJobServiceImpl underTest;

    @Test
    @DisplayName("It should create a job")
    void itShouldCreateAJob() {

        final String json = JsonUtil.getJsonFromFile(JOB_REQUEST_JSON_PATH);
        JobRequestDTO jobDTO = JsonUtil.jsonToObject(json, JobRequestDTO.class);
        final Job job = Job.builder()
            .id(UUID.randomUUID().toString())
            .title(jobDTO.getTitle())
            .description(jobDTO.getDescription())
            .skills(jobDTO.getSkills())
            .build();
        doReturn(job).when(repository).save(any());
        doReturn(job).when(mapper).toJob(jobDTO);

        assertDoesNotThrow(() -> underTest.createJob(jobDTO));
    }
}
