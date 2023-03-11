package com.bitwisemj.jobsapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobRequestDTO {
    
    private String title;
    private String description;
    private Integer experience;
    private List<String> skills;
}
