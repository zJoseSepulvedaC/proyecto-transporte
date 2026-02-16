package com.transporte.transport_bff.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VehiculoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // ENDPOINT 1: GPS (Hacia transport.gps.queue)
    @PostMapping("/vehiculos")
    public ResponseEntity<String> enviarGps(@RequestBody Map<String, Object> data) {
        try {
            String json = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend("transport.gps.queue", json);
            return ResponseEntity.ok(">>> ✅ Mensaje GPS enviado a la cola");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(">>>  Error en GPS: " + e.getMessage());
        }
    }

    // ENDPOINT 2: PLANIFICACIÓN (Hacia transport.schedule.queue)
    @PostMapping("/planificacion")
    public ResponseEntity<String> enviarPlanificacion(@RequestBody Map<String, Object> scheduleData) {
        try {
            String json = objectMapper.writeValueAsString(scheduleData);
            // Publica en la segunda cola (Criterio 3 de la rúbrica)
            rabbitTemplate.convertAndSend("transport.schedule.queue", json);
            return ResponseEntity.ok(">>> ✅ Planificación enviada a la cola schedule");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(">>>  Error en Planificación: " + e.getMessage());
        }
    }
}