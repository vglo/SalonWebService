package com.habbib;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class HabbibPaymentModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabbibPaymentModuleApplication.class, args);
	}
	
	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API").description("salon Billing Module REST API")
				.contact(new Contact("Yash Agrawal", "www.vglosoftwares.com", "agrawaly52@gmail.com")).version("1.0.0")
				.build();
	}

	@Bean
	public Docket api() throws IOException {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.habbib.controller")).paths(PathSelectors.any()).build()
				.apiInfo(apiEndPointsInfo());
	}

}
