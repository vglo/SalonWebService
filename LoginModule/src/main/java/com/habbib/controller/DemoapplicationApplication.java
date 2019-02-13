package com.habbib.controller;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xmlpull.v1.XmlPullParserException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import com.habbib.entity.LoginResponse;
import com.habbib.response.DefaultMessage;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.ApiInfoBuilder;
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class DemoapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoapplicationApplication.class, args);
	}



	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee REST CRUD operations API in Spring-Boot 2")
				.description(
						"Sample REST API for centalized documentation using Spring Boot and spring-fox swagger 2 ")
				.termsOfServiceUrl("").version("0.0.1-SNAPSHOT").contact(new Contact("Satish Sharma", "https://github.com/hellosatish", "https://github.com/hellosatish")).build();
	}
	
	@Bean
	public Docket api() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try {
			Model model = reader.read(new FileReader("pom.xml"));
		} catch (org.codehaus.plexus.util.xml.pull.XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new Docket(DocumentationType.SWAGGER_2)  
                .select() 
                .apis(RequestHandlerSelectors.basePackage(""))
                .paths(PathSelectors.any())                          
                .build().apiInfo(apiInfo());
	}
}


@RestController
@RequestMapping(value="Demo")
class DemoRestWeb{
	
	@RequestMapping(value="/webapp")
	public String createapp() {
		
	return "First WebApp";
		
	}
	
	/**
	* This is not actual code but a sample how DefaultMessage class is used for response of service.
	*/

	@RequestMapping(path = "/login", method = RequestMethod.GET, produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE	})
	//	@ApiOperation(value = "Used to login user.")
		public ResponseEntity<DefaultMessage<LoginResponse>> login(@RequestParam("username") String username,
				@RequestParam("password") String password) {

	        /**
	        * Code to check if user is authenticated or not.
	        */
			LoginResponse loginResponse = new LoginResponse();
			
			loginResponse.setName("Yash");
			
			DefaultMessage<LoginResponse> defaultMessage = new DefaultMessage<>();

	                // Set the response code.
			defaultMessage.setResponseCode("1");
	                // Set the response message
			defaultMessage.setResponseMessage("User authenticated successfully.");

	                // Set the response for successful login.
			defaultMessage.setResponse(loginResponse);

	        ResponseEntity<DefaultMessage<LoginResponse>> responseEntity = ResponseEntity.ok(defaultMessage);

			return responseEntity;
		}
	
	@RequestMapping(value="/createBill")
	public void testserver() {
		System.out.println("yash");
	}
}
