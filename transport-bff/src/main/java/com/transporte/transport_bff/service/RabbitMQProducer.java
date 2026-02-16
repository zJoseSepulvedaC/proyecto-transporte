package com.transporte.transport_bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String queueName, Object message) {
        try {
            String jsonString = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(queueName, jsonString);
            System.out.println(">>> 📤 ENVIADO A " + queueName + ": " + jsonString);
        } catch (JsonProcessingException e) {
            System.err.println(">>> ❌ ERROR AL SERIALIZAR MENSAJE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
