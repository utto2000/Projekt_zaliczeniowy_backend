package com.wator.organizer.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
@EnableRabbit
public class UserConfig {

    @Bean
    public Queue myQueue() {  // creates my-queue automatically on the RabbitMQ server if not available
        return new Queue("my-queue");
    }
}
