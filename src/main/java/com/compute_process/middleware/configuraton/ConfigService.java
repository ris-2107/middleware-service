package com.compute_process.middleware.configuraton;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@Configuration
@ConfigurationProperties(prefix = "compute.processes")
@AllArgsConstructor
public class ConfigService {

  private Map<String, String> config = new HashMap<>();

  public ConfigService() {
    config.put("DATA_PROCESSING", "taskQueue_TYPE_1");
    config.put("ANOMALY_DETECTION", "taskQueue_TYPE_2");
    config.put("MODEL_TRAINING", "taskQueue_TYPE_3");

    config.put("RABBIT_MQ_CONFIG", "amqps://qypgxsvh:eCL_9EIo1lbiu-0hUXf61g-ZTavOL_WM@puffin.rmq2.cloudamqp.com/qypgxsvh");
  }

  public String getProcessTypeToComputeNode(String key) {
    return config.get(key);
  }

  public String getConfigs(String key) {
    return config.get(key);
  }
}
