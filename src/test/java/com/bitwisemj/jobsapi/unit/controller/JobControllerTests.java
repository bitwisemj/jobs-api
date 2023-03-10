package com.bitwisemj.jobsapi.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitwisemj.jobsapi.configuration.RequestMappingConfig;
import com.bitwisemj.jobsapi.configuration.UnitTest;
import com.bitwisemj.jobsapi.controller.JobController;

@UnitTest
class JobControllerTests {
    
    private MockMvc mockMvc;
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
        
        mockMvc.perform(MockMvcRequestBuilders
            .post(RequestMappingConfig.JOB)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("It should get all jobs")
    void itShouldGetJobs() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .get(RequestMappingConfig.JOB))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
