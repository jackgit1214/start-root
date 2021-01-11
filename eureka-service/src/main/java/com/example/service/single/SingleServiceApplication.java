package com.example.service.single;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@EnableEurekaClient
public class SingleServiceApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SingleServiceApplication.class, args);
		new SpringApplicationBuilder(SingleServiceApplication.class)
				.properties("spring.config.location=classpath:/singleBootstrap.yml").run(args);
	}
}
