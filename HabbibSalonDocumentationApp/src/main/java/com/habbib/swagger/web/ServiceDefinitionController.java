/**
 * 
 */
package com.habbib.swagger.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.swagger.config.swagger.ServiceDefinitionsContext;

/**
 * @author yash
 *
 */
@RestController
public class ServiceDefinitionController {

	@Autowired
	private ServiceDefinitionsContext definitionContext;
	
	@GetMapping("/service/{servicename}")
	public String getServiceDefinition(@PathVariable("servicename") String serviceName){
		
		return definitionContext.getSwaggerDefinition(serviceName);
		
	}
}

