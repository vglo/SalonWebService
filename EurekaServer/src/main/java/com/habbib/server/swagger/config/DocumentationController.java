package com.habbib.server.swagger.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		resources.add(swaggerResource("billing-service", "/api/billing/v2/api-docs", "2.0"));
		resources.add(swaggerResource("login-service", "/api/loginmodule/v2/api-docs", "2.0"));
		resources.add(swaggerResource("db-service", "/api/db-service/v2/api-docs", "2.0"));
		resources.add(swaggerResource("sms-service", "/api/sms-api/v2/api-docs", "2.0"));
		resources.add(swaggerResource("customer-service", "/api/cust-api/v2/api-docs", "2.0"));
		resources.add(swaggerResource("shop-service", "/api/shop-api/v2/api-docs", "2.0"));
		resources.add(swaggerResource("pdf-service", "/api/pdf-api/v2/api-docs", "2.0"));
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
