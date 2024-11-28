package com.example.NeymoAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NeymoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeymoApiApplication.class, args);
	}

}
