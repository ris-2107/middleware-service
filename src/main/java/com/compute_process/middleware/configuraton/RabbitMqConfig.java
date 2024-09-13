package com.compute_process.middleware.configuraton;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {
  @Autowired private final ConfigService configService;

  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    String rabbitMqUrl=  configService.getConfigs("RABBIT_MQ_CONFIG");
    connectionFactory.setUri(rabbitMqUrl);
    return connectionFactory;
  }

  @Bean
  public Queue queue() {
    return new Queue("taskQueue", false);
  }

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange("taskExchange");
  }

  @Bean
  public Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("routingKey");
  }
}
