package com.agenda.agenda_hexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.common.lang.NonNull;

@SpringBootApplication
public class AgendaHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaHexagonalApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
			@Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")                      // Todos los endpoints
                        .allowedOrigins("http://localhost:4200") // Desde Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }

}
