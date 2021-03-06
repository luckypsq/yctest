package com.yc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SpringCloudUser {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudUser.class, args);
	}

	// 负载均衡注解
		@LoadBalanced
		@Bean
		public RestTemplate geTemplate() {
			return new RestTemplate();
		}
}
