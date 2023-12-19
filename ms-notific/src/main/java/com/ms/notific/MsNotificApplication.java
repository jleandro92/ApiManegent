package com.ms.notific;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ms.notific.configs.EmailConfig;

@SpringBootApplication
@Import(EmailConfig.class)
public class MsNotificApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificApplication.class, args);
	}

}
