package com.bitwisemj.jobsapi.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bitwisemj.jobsapi.configuration.JsonUtil;
import com.bitwisemj.jobsapi.configuration.UnitTest;
import com.bitwisemj.jobsapi.dto.JobRequestDTO;
import com.bitwisemj.jobsapi.dto.JobResponseDTO;
import com.bitwisemj.jobsapi.entity.Job;
import com.bitwisemj.jobsapi.mapper.JobMapperImpl;

@UnitTest
public class JobMapperTests {
    
    private static final String JOB_REQUEST_JSON_PATH = 
    "src/test/resources/json/job-request.json";
    private JobMapperImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new JobMapperImpl();
    }

    @Test
    @DisplayName("It should map job dto to job entity")
    void itShouldMapJobDTOToJobEntity() {
        
        final String json = JsonUtil.getJsonFromFile(JOB_REQUEST_JSON_PATH);
        final JobRequestDTO jobDTO = JsonUtil.jsonToObject(json, JobRequestDTO.class);

        final Job job = underTest.toJob(jobDTO);

        assertEquals("DevOps", job.getTitle());
        assertEquals("Big knowledge on AWS, Google Cloud, Azure", job.getDescription());
        assertEquals(List.of("AWS", "Google Cloud", "Azure"), job.getSkills());
    }

    @Test
    @DisplayName("It should map job to job response dto")
    void itShouldMapJobToJobResponseDTO() {
        
        final String id = UUID.randomUUID().toString();
        final String json = JsonUtil.getJsonFromFile(JOB_REQUEST_JSON_PATH);
        final JobRequestDTO jobDTO = JsonUtil.jsonToObject(json, JobRequestDTO.class);
        final Job job = Job.builder()
            .id(id)
            .title(jobDTO.getTitle())
            .description(jobDTO.getDescription())
            .skills(jobDTO.getSkills())
            .build();
        final JobResponseDTO jobResponseDTO = underTest.toJobResponseDTO(job);
        
        assertEquals("DevOps", jobResponseDTO.getTitle());
        assertEquals("Big knowledge on AWS, Google Cloud, Azure", jobResponseDTO.getDescription());
        assertEquals(List.of("AWS", "Google Cloud", "Azure"), jobResponseDTO.getSkills());
    }
}
