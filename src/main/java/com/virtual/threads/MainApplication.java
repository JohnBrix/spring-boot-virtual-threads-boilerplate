package com.virtual.threads;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(MainApplication.class, args);
		}catch (Exception e){
			log.error(e.getMessage());
		}

	}


}
