package com.consul.demo.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProviderController {

	private final DiscoveryClient discoveryClient;

	public ProviderController(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@GetMapping(value = "getInfo")
	public String test(){
		List<String> services = discoveryClient.getServices();
		StringBuffer sb = new StringBuffer();
		sb.append("hello spring cloud ! ")
				.append("\n")
				.append(" hello consul!");
		for(String s : services){
			log.info(s);
			sb.append("\n")
					.append(s);
		}
		return sb.toString();
	}


}
