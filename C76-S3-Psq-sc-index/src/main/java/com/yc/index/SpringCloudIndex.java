package com.yc.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCloudIndex {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudIndex.class, args);
	}

	//负载均衡注解
	@LoadBalanced
	@Bean
	public RestTemplate geTemplate() {
		return new RestTemplate();
	}
}
