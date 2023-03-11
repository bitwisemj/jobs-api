package com.bitwisemj.jobsapi.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitwisemj.jobsapi.configuration.JsonUtil;
import com.bitwisemj.jobsapi.configuration.RequestMappingConfig;
import com.bitwisemj.jobsapi.configuration.UnitTest;
import com.bitwisemj.jobsapi.controller.JobController;
import com.bitwisemj.jobsapi.dto.JobResponseDTO;
import com.bitwisemj.jobsapi.exception.ApplicationException;
import com.bitwisemj.jobsapi.service.CreateJobService;
import com.bitwisemj.jobsapi.service.GetJobService;

@UnitTest
class JobControllerTests {
    
    private static final String JOB_REQUEST_JSON_PATH = 
    "src/test/resources/json/job-request.json";
    private static final String JOB_RESPONSE_JSON_PATH = 
    "src/test/resources/json/job-response.json";
    private MockMvc mockMvc;
    @Mock
    private CreateJobService createJobService;
    @Mock
    private GetJobService getJobService;
    @InjectMocks
    private JobController underTest;

    @BeforeEach
    void setUp() {
        
        mockMvc = MockMvcBuilders
        .standaloneSetup(underTest)
        .build();
    }
    
    @Test
    @DisplayName("It should create a job")
    void itShouldCreateAJob() throws Exception {
        
        final String jsonRequest = JsonUtil.getJsonFromFile(JOB_REQUEST_JSON_PATH);
        doNothing().when(createJobService).createJob(any());

        mockMvc.perform(MockMvcRequestBuilders
            .post(RequestMappingConfig.JOB)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("It should return internal server error when creating job")
    void itShouldReturnInternalServerError() throws Exception {

        final String jsonRequest = JsonUtil.getJsonFromFile(JOB_REQUEST_JSON_PATH);
        
        doThrow(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, 
        "Unexpected error - Internal server error"))
            .when(createJobService).createJob(any());

        mockMvc.perform(MockMvcRequestBuilders.post(RequestMappingConfig.JOB)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    @DisplayName("It should get all jobs")
    void itShouldGetJobs() throws Exception {

        final String json = JsonUtil.getJsonFromFile(JOB_RESPONSE_JSON_PATH);
        JobResponseDTO[] jobs = JsonUtil.jsonToObject(json, JobResponseDTO[].class);
        doReturn(List.of(jobs)).when(getJobService).getJobs();

        mockMvc.perform(MockMvcRequestBuilders
            .get(RequestMappingConfig.JOB))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("It should return internal server error when getting jobs")
    void itShouldReturnInternalServerErrorWhenFailToGetJobs() throws Exception {
        
        doThrow(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, 
        "Unexpected error - Internal server error"))
            .when(getJobService).getJobs();

        mockMvc.perform(MockMvcRequestBuilders.get(RequestMappingConfig.JOB))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    @DisplayName("It should get jobs by experience")
    void itShouldGetJobsByExperience() throws Exception {

        final String json = JsonUtil.getJsonFromFile(JOB_RESPONSE_JSON_PATH);
        JobResponseDTO[] jobs = JsonUtil.jsonToObject(json, JobResponseDTO[].class);
        doReturn(List.of(jobs)).when(getJobService).getJobsByExperience(anyInt(), anyInt());

        mockMvc.perform(MockMvcRequestBuilders.get(RequestMappingConfig.JOB + "/by-experience")
            .param("minXp", "3")
            .param("maxXp", "3"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("It should return internal server error when get jobs by experience")
    void itShouldReturnInternalServerErrorWhenGetJobsByExperience() throws Exception {
        
        doThrow(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, 
        "Unexpected error - Internal server error"))
            .when(getJobService).getJobsByExperience(anyInt(), anyInt());

        mockMvc.perform(MockMvcRequestBuilders.get(RequestMappingConfig.JOB + "/by-experience")
        .param("minXp", "5")
        .param("maxXp", "10"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
