package com.transporte.transport_bff.controller;

import com.transporte.transport_bff.config.RabbitMQConfig;
import com.transporte.transport_bff.dto.GpsDataDTO;
import com.transporte.transport_bff.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gps")
public class GpsController {

    @Autowired
    private RabbitMQProducer producer;

    @PostMapping
    public ResponseEntity<String> sendGpsData(@RequestBody GpsDataDTO gpsData) {
        producer.sendMessage(RabbitMQConfig.QUEUE_GPS, gpsData);
        return ResponseEntity.ok("Datos GPS enviados correctamente.");
    }

    @GetMapping
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok("Servicio GPS Operativo");
    }
}
