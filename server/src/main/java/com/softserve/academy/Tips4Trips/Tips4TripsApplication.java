package com.softserve.academy.Tips4Trips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Tips4TripsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tips4TripsApplication.class, args);
	}

}
