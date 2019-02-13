package com.habbib.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class HabbibSalonBillingModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabbibSalonBillingModuleApplication.class, args);
	}
	


}

