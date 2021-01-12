package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
public class RegistrationClient {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationClient.class, args);
	}
}

@RestController
class ClientRestController {

	private final DiscoveryClient discoveryClient;

	public ClientRestController(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@RequestMapping("/service/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {

		List<ServiceInstance> services = this.discoveryClient.getInstances(applicationName);

		return services;
	}

	@RequestMapping("/service")
	public List<String> getAllServices() {

		List<String> services = this.discoveryClient.getServices();

		return services;
	}
}
