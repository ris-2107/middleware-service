package com.compute_process.middleware;

import com.compute_process.middleware.configuraton.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigService.class)
public class MiddlewareApplication {

  public static void main(String[] args) {
    SpringApplication.run(MiddlewareApplication.class, args);
  }
}
