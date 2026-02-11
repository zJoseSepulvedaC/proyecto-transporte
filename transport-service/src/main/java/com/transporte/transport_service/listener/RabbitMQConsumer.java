package com.transporte.transport_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "cola_transporte")
    public void recibirMensaje(String mensaje) {
        System.out.println("=================================================");
        System.out.println(">>> 🚚 TRANSPORT-SERVICE RECIBIÓ EL MENSAJE: " + mensaje);
        System.out.println("=================================================");
    }
}