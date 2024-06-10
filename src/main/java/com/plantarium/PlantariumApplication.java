package com.plantarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PlantariumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantariumApplication.class, args);
	}

}
