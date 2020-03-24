package com.yc.ebuy;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.yc.ebuy")
//开启声明式远程服务调用
@EnableFeignClients
public class BackEbuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEbuyApplication.class, args);
	}
}
