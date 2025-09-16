package com.example.springSecurityTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springSecurityTask"})
public class SpringSecurityTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityTaskApplication.class, args);
	}

}
