package com.compute_process.middleware.service;

import com.compute_process.middleware.configuraton.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComputeNodeDiscovery {
  private static final Logger log = LoggerFactory.getLogger(ComputeNodeDiscovery.class);
  @Autowired
  ConfigService configService;


  public String getTargetQueueForComputeType(String computeType) {
    return configService.getProcessTypeToComputeNode(computeType);
  }
}
