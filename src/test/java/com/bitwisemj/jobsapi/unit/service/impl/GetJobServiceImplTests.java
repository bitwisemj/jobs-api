package com.bitwisemj.jobsapi.unit.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.bitwisemj.jobsapi.configuration.JsonUtil;
import com.bitwisemj.jobsapi.configuration.UnitTest;
import com.bitwisemj.jobsapi.entity.Job;
import com.bitwisemj.jobsapi.mapper.JobMapper;
import com.bitwisemj.jobsapi.repository.JobRepository;
import com.bitwisemj.jobsapi.service.impl.GetJobServiceImpl;

@UnitTest
public class GetJobServiceImplTests {
    
    private static final String JOB_RESPONSE_JSON_PATH = 
    "src/test/resources/json/job-response.json";
    @Mock
    private JobRepository repository;
    @Mock
    private JobMapper mapper;
    @InjectMocks
    private GetJobServiceImpl underTest;

    @Test
    @DisplayName("It should get all jobs")
    void itShouldGetAllJobs() {

        final String json = JsonUtil.getJsonFromFile(JOB_RESPONSE_JSON_PATH);
        Job[] jobs = JsonUtil.jsonToObject(json, Job[].class);
        doReturn(List.of(jobs)).when(repository).findAll();

        assertNotNull(underTest.getJobs());
    }

    @Test
    @DisplayName("It should get jobs by experience")
    void itShouldGetJobsByExperience() {

        final Integer minXp = 4;
        final Integer maxXp = 5;
        final String json = JsonUtil.getJsonFromFile(JOB_RESPONSE_JSON_PATH);
        Job[] jobs = JsonUtil.jsonToObject(json, Job[].class);
        doReturn(List.of(jobs)).when(repository).findByExperience(anyInt(), anyInt());

        assertNotNull(underTest.getJobsByExperience(minXp, maxXp));
    }
}
