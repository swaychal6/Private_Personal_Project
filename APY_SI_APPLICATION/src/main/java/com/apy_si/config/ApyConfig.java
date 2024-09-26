package com.apy_si.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApyConfig {

	private static  Environment environment;
	
	@Autowired
	public void setEnviorment(Environment environment) {
		ApyConfig.environment=environment;
	}
	

	public static String getProperty(String propertyKey) {
		return environment.getProperty(propertyKey);
	}

}
