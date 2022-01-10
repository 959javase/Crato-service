package com.nash.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NashcloudProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(NashcloudProductApplication.class, args);
	}
}
