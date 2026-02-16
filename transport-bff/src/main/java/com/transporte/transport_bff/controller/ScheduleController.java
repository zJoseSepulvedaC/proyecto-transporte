package com.transporte.transport_bff.controller;

import com.transporte.transport_bff.config.RabbitMQConfig;
import com.transporte.transport_bff.dto.ScheduleDataDTO;
import com.transporte.transport_bff.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private RabbitMQProducer producer;

    @PostMapping
    public ResponseEntity<String> sendScheduleData(@RequestBody ScheduleDataDTO scheduleData) {
        producer.sendMessage(RabbitMQConfig.QUEUE_SCHEDULE, scheduleData);
        return ResponseEntity.ok("Datos de Horario enviados correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> updateSchedule() {
        return ResponseEntity.ok("Simulación Update OK");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSchedule() {
        return ResponseEntity.ok("Simulación Delete OK");
    }
}
