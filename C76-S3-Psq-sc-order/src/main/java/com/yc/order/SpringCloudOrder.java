package com.yc.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SpringCloudOrder {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOrder.class, args);
	}

	// 负载均衡注解
	@LoadBalanced
	@Bean
	public RestTemplate geTemplate() {
		return new RestTemplate();
	}
}
