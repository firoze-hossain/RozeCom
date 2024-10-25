package com.roze.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.roze.common.entity"})
public class RozeEcomFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RozeEcomFrontEndApplication.class, args);
	}

}
