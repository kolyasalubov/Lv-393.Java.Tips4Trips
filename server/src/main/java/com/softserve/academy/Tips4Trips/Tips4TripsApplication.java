package com.softserve.academy.Tips4Trips;

import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.FileStorageService;
import com.softserve.academy.Tips4Trips.service.ImageService;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class Tips4TripsApplication implements CommandLineRunner {

	@Autowired
	FileStorageService imageService;

	public static void main(String[] args) throws FileIOException {
		SpringApplication.run(Tips4TripsApplication.class, args);
	}

//	@Bean
//	public ModelMapper modelMapper() {
//		ModelMapper mapper = new ModelMapper();
//		mapper.getConfiguration()
//				.setMatchingStrategy(MatchingStrategies.STRICT)
//				.setFieldMatchingEnabled(true)
//				.setSkipNullEnabled(true)
//				.setFieldAccessLevel(PRIVATE);
//		return mapper;
//	}

	@Override
	public void run(String... arg) throws Exception {
//		imageService.init();
	}

}
