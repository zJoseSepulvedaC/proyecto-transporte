package com.transporte.transport_bff;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/bff")
public class BffController {

    // Lee la URL del backend desde el docker-compose o usa una por defecto
    @Value("${TRANSPORT_SERVICE_URL:http://transport-service:8081}")
    private String backendUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/vehicles")
    public List<Object> getVehicles() {
        // Concatena la URL: http://transport-service:8081/api/vehicles
        String url = backendUrl + "/api/vehicles";
        
        // Hace la llamada al otro microservicio
        Object[] vehicles = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(vehicles);
    }
}