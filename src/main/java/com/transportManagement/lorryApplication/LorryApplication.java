package com.transportManagement.lorryApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LorryApplication {

	public static void main(String[] args) {
        System.out.println("Starting the application");
		SpringApplication.run(LorryApplication.class, args);
	}

}
