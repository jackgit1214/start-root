package com.consul.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;;

@RestController
@Slf4j
public class ConsumerController {

	private final LoadBalancerClient loadBalancer;

	private final DiscoveryClient discoveryClient;

	private final RestTemplate restTemplate;

	private final static String SERVICE_NAME = "consul-provider";

	public ConsumerController(LoadBalancerClient loadBalancer,
			DiscoveryClient discoveryClient,
			RestTemplate restTemplate) {
		this.loadBalancer = loadBalancer;
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
	}


	/**
	 * 使用普通的 RestTemplate 方法访问服务
	 *
	 * @return
	 */
	@GetMapping(value = "consumerGetInfo")
	public Object getInfo() {
		log.info("消息者方法执行..................."+loadBalancer.choose(SERVICE_NAME).getUri().toString());
		String result = restTemplate.getForObject("http://"+SERVICE_NAME + "/getInfo", String.class);
		log.info(result);
		result = result+"\n"+loadBalancer.choose(SERVICE_NAME).getUri().toString();
		return result;
	}


	/**
	 * 获取所有服务实例
	 *
	 * @return
	 */
	@GetMapping(value = "/services")
	public Object services() {
		return discoveryClient.getInstances(SERVICE_NAME);
	}

	/**
	 * 从所有服务中选择一个服务（轮询）
	 */
	@GetMapping(value = "/choose")
	public Object choose() {
		return loadBalancer.choose(SERVICE_NAME).getUri().toString();
	}

}
