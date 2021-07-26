package com.lowes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lowes")
public class LowesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LowesApplication.class, args);
	}

}
