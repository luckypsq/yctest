package com.yc.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启声明式远程服务调用
@EnableFeignClients
//服务熔断降级开关
@EnableCircuitBreaker
//开启zuul网关服务
@EnableZuulProxy
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
