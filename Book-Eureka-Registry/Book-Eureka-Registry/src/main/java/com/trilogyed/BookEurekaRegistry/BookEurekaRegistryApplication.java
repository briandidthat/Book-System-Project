package com.trilogyed.BookEurekaRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookEurekaRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookEurekaRegistryApplication.class, args);
	}

}
