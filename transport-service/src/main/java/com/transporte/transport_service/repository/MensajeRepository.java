package com.transporte.transport_service.repository;

import com.transporte.transport_service.model.Mensaje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}