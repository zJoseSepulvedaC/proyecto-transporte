package com.transporte.transport_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transporte.transport_service.model.Vehiculo;
import com.transporte.transport_service.repository.VehiculoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // <--- AGREGA ESTO

@Service
public class RabbitMQConsumer {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @RabbitListener(queues = "transport.schedule.queue")
    public void recibirMensajeSchedule(String mensaje) {
        System.out.println("=================================================");
        System.out.println(">>> 🚚 TRANSPORT-SERVICE RECIBIÓ EL MENSAJE (SCHEDULE): " + mensaje);

        try {
            // Lógica para escribir en archivo JSON
            String filePath = "/app/data-output/schedule_ticket.json";
            java.io.File file = new java.io.File(filePath);

            // Asegurar que el directorio existe
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            // Escribir el mensaje en el archivo
            try (java.io.FileWriter writer = new java.io.FileWriter(file, true)) { // true para append
                writer.write(mensaje + "\n");
                System.out.println(">>> 💾 MENSAJE GUARDADO EN: " + filePath);
            }
        } catch (Exception e) {
            System.err.println(">>> ❌ ERROR AL GUARDAR EN ARCHIVO: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=================================================");
    }

    @RabbitListener(queues = "transport.gps.queue")
    public void recibirMensajeGps(String mensaje) {
        System.out.println("=================================================");
        System.out.println(">>> 🛰️ TRANSPORT-SERVICE RECIBIÓ EL MENSAJE (GPS): " + mensaje);

        try {
            // Convertir JSON a objeto Vehiculo
            // El JSON debe coincidir con campos de Vehiculo o ser mapeado
            Vehiculo vehiculo = objectMapper.readValue(mensaje, Vehiculo.class);

            // Asignar fecha de registro si no viene en el JSON
            if (vehiculo.getFechaRegistro() == null) {
                vehiculo.setFechaRegistro(LocalDateTime.now());
            }

            // Guardar en base de datos
            vehiculoRepository.save(vehiculo);
            System.out.println(">>> 💾 GPS DATA GUARDADO EN ORACLE: ID=" + vehiculo.getId());

        } catch (Exception e) {
            System.err.println(">>> ❌ ERROR AL PROCESAR MENSAJE GPS: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=================================================");
    }
}
