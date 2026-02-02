package com.transporte.transport_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    @Autowired
    private MensajeRepository mensajeRepository;

    // "cola_transporte" debe coincidir EXACTO con el nombre que pusiste en RabbitMQ
    @RabbitListener(queues = "cola_transporte")
    public void recibirMensaje(String mensajeRecibido) {
        System.out.println(">>> 🔔 MENSAJE RECIBIDO DE RABBITMQ: " + mensajeRecibido);
        
        // Guardamos el mensaje en Oracle
        try {
            Mensaje nuevoMensaje = new Mensaje();
            nuevoMensaje.setContenido(mensajeRecibido);
            mensajeRepository.save(nuevoMensaje);
            System.out.println(">>> ✅ Guardado exitosamente en Oracle DB");
        } catch (Exception e) {
            System.err.println(">>> ❌ Error guardando en BD: " + e.getMessage());
        }
    }
}