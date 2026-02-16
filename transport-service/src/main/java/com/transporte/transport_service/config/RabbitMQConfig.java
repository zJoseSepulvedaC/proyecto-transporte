package com.transporte.transport_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue gpsQueue() {
        return new Queue("transport.gps.queue", true);
    }

    @Bean
    public Queue scheduleQueue() {
        return new Queue("transport.schedule.queue", true);
    }
}