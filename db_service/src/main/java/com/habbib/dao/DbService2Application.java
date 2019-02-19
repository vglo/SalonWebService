package com.habbib.dao;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableEurekaClient
@EnableSwagger2
public class DbService2Application {

	public static void main(String[] args) {
		SpringApplication.run(DbService2Application.class, args);
	}
	
	
	 private ApiInfo apiEndPointsInfo() {
	        return new ApiInfoBuilder().title("Spring Boot REST API")
	            .description("salon Database Module REST API")
	            .contact(new Contact("Yash Agrawal", "www.vglosoftwares.com", "agrawaly52@gmail.com"))
	            .version("1.0.0")
	            .build();
	    }
	
	@Bean
	public Docket api() throws IOException, XmlPullParserException {
     MavenXpp3Reader reader = new MavenXpp3Reader();
     Model model = reader.read(new FileReader("pom.xml"));
     return new Docket(DocumentationType.SWAGGER_2)  
             .select() 
             .apis(RequestHandlerSelectors.basePackage("com.habbib.dao"))
             .paths(PathSelectors.any())                          
             .build().apiInfo(apiEndPointsInfo());
	}
}

