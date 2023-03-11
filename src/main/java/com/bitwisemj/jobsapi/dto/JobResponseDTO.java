package com.bitwisemj.jobsapi.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobResponseDTO implements Serializable {
    
    private String id;
    private String title;
    private Integer experience;
    private String description;
    private List<String> skills;
}
