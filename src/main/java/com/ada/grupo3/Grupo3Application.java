package com.ada.grupo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ada.grupo3")
public class Grupo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Grupo3Application.class, args);
	}

}
