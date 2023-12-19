package com.example.apibus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.apibus", "com.ms.notific.services"})
public class ApibusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApibusApplication.class, args);
	}

}
