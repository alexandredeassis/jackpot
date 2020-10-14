package com.ajasoft.jackpot.jackpotcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableWebFlux
public class JackspotCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JackspotCoreApplication.class, args);
	}



}
