package com.compute_process.middleware.api;

import com.compute_process.middleware.dtos.ComputeRequest;
import com.compute_process.middleware.model.TaskResult;
import com.compute_process.middleware.service.ProcessQueueService;
import com.compute_process.middleware.service.interfaces.IProcessQueueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process-queue")
@AllArgsConstructor
public class ProcessQueueController {

  @Autowired private final IProcessQueueService processQueueService;

  @PostMapping("/trigger")
  public ResponseEntity<String> triggerComputeProcess(@RequestBody ComputeRequest computeRequest) {
    processQueueService.enqueueTask(computeRequest);
    return ResponseEntity.ok("Task Enqueued Successfully");
  }

  @PostMapping("/status")
  public ResponseEntity<TaskResult> getTaskStatus(@RequestBody ComputeRequest computeRequest) {
    TaskResult status = processQueueService.getTaskStatus(computeRequest);
    return ResponseEntity.ok(status);
  }
}
