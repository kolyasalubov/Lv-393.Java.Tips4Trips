package com.softserve.academy.Tips4Trips;

import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class Tips4TripsApplication implements CommandLineRunner {

	@Autowired
	ImageService imageService;

	public static void main(String[] args) throws FileIOException {
		SpringApplication.run(Tips4TripsApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		imageService.init();
	}
}
