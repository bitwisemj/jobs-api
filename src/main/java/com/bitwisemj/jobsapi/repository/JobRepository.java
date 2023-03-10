package com.bitwisemj.jobsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bitwisemj.jobsapi.entity.Job;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    
}
