package com.transporte.transport_service.entity;

import jakarta.persistence.*; // Si te da error, prueba con javax.persistence.*
import java.time.LocalDateTime;

@Entity
@Table(name = "MENSAJES_RABBIT")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    @Column(name = "FECHA_RECEPCION")
    private LocalDateTime fechaRecepcion = LocalDateTime.now();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}