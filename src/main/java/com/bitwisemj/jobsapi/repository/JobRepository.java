package com.bitwisemj.jobsapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bitwisemj.jobsapi.entity.Job;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    
    @Query("{experience: {$gt: ?0, $lt: ?1}}")
    List<Job> findByExperience(final Integer minXp, final Integer maxXp);
}
