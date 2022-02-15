package com.clients.clientsapi.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

// Ao usar o formato JSON, Spring Boot usará uma instância ObjectMapper para serializar respostas e desserializar requests.
// configura a serialização e desserialização de objetos LocalDate
@Configuration
public class LocalDateConfig {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final LocalDateSerializer LOCAL_DATE_SERIALIZER = new LocalDateSerializer(
			DateTimeFormatter.ofPattern(LocalDateConfig.DATE_FORMAT));
	
	@Bean
	@Primary
	public ObjectMapper createObjectMapper() {
		JavaTimeModule module = new JavaTimeModule();
		module.addSerializer(LocalDate.class, LocalDateConfig.LOCAL_DATE_SERIALIZER);
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(module);
		return objMapper;
	}
}
