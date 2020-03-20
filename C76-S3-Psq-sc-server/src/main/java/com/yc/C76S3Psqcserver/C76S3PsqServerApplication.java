package com.yc.C76S3Psqcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class C76S3PsqServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(C76S3PsqServerApplication.class, args);
	}

}
