package com.sap.aish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootJpaWithEclipseLinkApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaWithEclipseLinkApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootJpaWithEclipseLinkApplication.class); 
	}
}
