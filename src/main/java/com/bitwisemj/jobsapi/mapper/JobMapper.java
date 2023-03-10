package com.bitwisemj.jobsapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.bitwisemj.jobsapi.dto.JobRequestDTO;
import com.bitwisemj.jobsapi.dto.JobResponseDTO;
import com.bitwisemj.jobsapi.entity.Job;

@Mapper(componentModel = "spring")
public interface JobMapper {
    
    @Mappings(value = {
        @Mapping(source = "jobDTO.title", target = "title"),
        @Mapping(source = "jobDTO.description", target = "description"),
        @Mapping(source = "jobDTO.skills", target = "skills")
    })
    public Job toJob(final JobRequestDTO jobDTO);

    @Mappings(value = {
        @Mapping(source = "job.id", target = "id"),
        @Mapping(source = "job.title", target = "title"),
        @Mapping(source = "job.description", target = "description"),
        @Mapping(source = "job.skills", target = "skills")
    })
    public JobResponseDTO toJobResponseDTO(final Job job);
}
