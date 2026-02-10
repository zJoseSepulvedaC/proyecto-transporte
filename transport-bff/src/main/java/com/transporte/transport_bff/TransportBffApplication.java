package com.transporte.transport_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@SpringBootApplication
public class TransportBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportBffApplication.class, args);
	}

	// AQUI metemos la seguridad "a la fuerza" para que no la ignore
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable) // Desactiva proteccion de formularios
				.authorizeHttpRequests(auth -> auth
						.anyRequest().permitAll() // DEJA PASAR TODO
				);
		return http.build();
	}
}