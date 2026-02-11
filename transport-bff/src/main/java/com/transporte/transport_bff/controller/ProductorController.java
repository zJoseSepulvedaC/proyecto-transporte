package com.transporte.transport_bff.controller; // <--- ESTO ES LO QUE FALTABA

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class ProductorController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String NOMBRE_COLA = "cola_transporte";

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarMensaje(@RequestBody String mensaje) {
        try {
            rabbitTemplate.convertAndSend(NOMBRE_COLA, mensaje);
            System.out.println(">>> 📤 MENSAJE ENVIADO A LA COLA: " + mensaje);
            return ResponseEntity.ok("Mensaje enviado correctamente a RabbitMQ: " + mensaje);
        } catch (Exception e) {
            System.err.println(">>> ❌ ERROR AL ENVIAR MENSAJE: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar mensaje: " + e.getMessage());
        }
    }
}