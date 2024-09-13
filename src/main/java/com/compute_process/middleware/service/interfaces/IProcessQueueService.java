package com.compute_process.middleware.service.interfaces;

import com.compute_process.middleware.dtos.ComputeRequest;
import com.compute_process.middleware.model.TaskResult;

public interface IProcessQueueService {

  void enqueueTask(ComputeRequest request);
  TaskResult getTaskStatus(ComputeRequest computeRequest);
}
