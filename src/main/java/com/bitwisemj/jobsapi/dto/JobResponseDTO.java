package com.bitwisemj.jobsapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobResponseDTO {
    
    private String id;
    private String title;
    private String description;
    private List<String> skills;
}
