package com.yc.ebuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.yc.ebuy")
public class CommonEbuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonEbuyApplication.class, args);
	}

}
