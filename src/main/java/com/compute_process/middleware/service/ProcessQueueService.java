package com.compute_process.middleware.service;

import com.compute_process.middleware.dtos.ComputeRequest;
import com.compute_process.middleware.model.TaskResult;
import com.compute_process.middleware.repository.TaskResultRepository;
import com.compute_process.middleware.service.interfaces.IProcessQueueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcessQueueService implements IProcessQueueService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired private RabbitTemplate rabbitTemplate;
  @Autowired private TaskResultRepository taskResultRepository;
  @Autowired private ComputeNodeDiscovery discovery;

  @Override
  public void enqueueTask(ComputeRequest request) {
    String QUEUE_NAME = discovery.getTargetQueueForComputeType(request.getComputeType());
    log.info(QUEUE_NAME);

    // If there are multiple services running
    try {
      rabbitTemplate.convertAndSend(QUEUE_NAME, objectMapper.writeValueAsString(request));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public TaskResult getTaskStatus(ComputeRequest computeRequest) {
    return taskResultRepository
        .findById(computeRequest.getComputeType() + "_" + computeRequest.getUserId())
        .orElseThrow(() -> new RuntimeException("Task not found"));
  }
}
