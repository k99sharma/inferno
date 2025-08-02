package com.k99sharma.simulation;

import com.k99sharma.inferno.annotations.EnableInferno;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableInferno(profiles = {"dev"})
public class InfernoSimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfernoSimulationApplication.class, args);
	}

}
