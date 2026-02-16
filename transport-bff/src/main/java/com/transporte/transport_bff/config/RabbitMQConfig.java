package com.transporte.transport_bff.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_GPS = "transport.gps.queue";
    public static final String QUEUE_SCHEDULE = "transport.schedule.queue";

    @Bean
    public Queue gpsQueue() {
        return new Queue(QUEUE_GPS, true); // true = durable
    }

    @Bean
    public Queue scheduleQueue() {
        return new Queue(QUEUE_SCHEDULE, true); // true = durable
    }
}
