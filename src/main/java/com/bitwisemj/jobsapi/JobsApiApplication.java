package com.bitwisemj.jobsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsApiApplication.class, args);
	}

}
