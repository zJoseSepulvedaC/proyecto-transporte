package com.transporte.transport_bff; // Asegúrate de que este paquete sea correcto según tu carpeta

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductorController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // IMPORTANTE: Este nombre debe ser IDÉNTICO al que usaste en el Consumer
    // En tu foto "image_a18aa5.png" vi que se llama "cola_transporte"
    private static final String NOMBRE_COLA = "cola_transporte";

    @PostMapping("/enviar")
    public org.springframework.http.ResponseEntity<String> enviarMensaje(@RequestBody String mensaje) {
        try {
            // Aquí enviamos el mensaje a RabbitMQ
            rabbitTemplate.convertAndSend(NOMBRE_COLA, mensaje);

            System.out.println(">>> 📤 MENSAJE ENVIADO A LA COLA: " + mensaje);
            return org.springframework.http.ResponseEntity.ok("Mensaje enviado correctamente a RabbitMQ: " + mensaje);
        } catch (Exception e) {
            System.err.println(">>> ❌ ERROR AL ENVIAR MENSAJE: " + e.getMessage());
            e.printStackTrace();
            return org.springframework.http.ResponseEntity
                    .status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar mensaje: " + e.getMessage());
        }
    }
}