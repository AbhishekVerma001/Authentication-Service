package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RetailWebAppAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailWebAppAuthenticationServiceApplication.class, args);
	}

}
