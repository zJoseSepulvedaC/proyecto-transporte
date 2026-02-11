package com.transporte.transport_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.transporte.transport_service.entity.Vehicle;
import com.transporte.transport_service.repository.VehicleRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository repository;

    @GetMapping
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        Vehicle vehicle = repository.findById(id).orElseThrow();
        vehicle.setPatente(vehicleDetails.getPatente());
        vehicle.setModelo(vehicleDetails.getModelo());
        vehicle.setEstado(vehicleDetails.getEstado());
        vehicle.setLatitud(vehicleDetails.getLatitud());
        vehicle.setLongitud(vehicleDetails.getLongitud());
        return repository.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}