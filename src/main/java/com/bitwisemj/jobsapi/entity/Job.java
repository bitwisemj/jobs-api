package com.bitwisemj.jobsapi.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    
    @Id
    private String id;

    private String title;

    private String description;

    private List<String> skills;
}
