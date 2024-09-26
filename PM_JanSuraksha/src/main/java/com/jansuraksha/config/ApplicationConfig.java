package com.jansuraksha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@PropertySources({ 
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:ArkToAadharAPI.properties"),
		@PropertySource("classpath:JanSurakshaAPI.properties"),
		@PropertySource("classpath:MasterPolicyNumber.properties")})
public class ApplicationConfig {

	public static Environment environment;

	@Autowired
	public void setEnviorment(Environment environment) {
		ApplicationConfig.environment = environment;
	}

	public static String getProperty(String propertyKey) {
		return environment.getProperty(propertyKey);
	}

	@Bean
	public WebClient.Builder webClient() {
		return WebClient.builder();
	}
}
