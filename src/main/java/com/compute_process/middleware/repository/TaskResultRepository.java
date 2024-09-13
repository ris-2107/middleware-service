package com.compute_process.middleware.repository;


import com.compute_process.middleware.model.TaskResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskResultRepository extends MongoRepository<TaskResult, String> {

}

