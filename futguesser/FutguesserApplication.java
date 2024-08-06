package com.futguesser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.futguesser"})
@EntityScan(basePackages = {"com.futguesser.entities"})
public class FutguesserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutguesserApplication.class, args);
	}

}

