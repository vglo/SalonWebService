package com.habbib.security.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.habbib.security.feign.fallback.DBServiceFallback;
import com.habbib.security.response.model.Role;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {
	

	
	@RequestMapping(path="/dao/delete-role/{id}", method=RequestMethod.DELETE)
	public void deleteRole(@PathVariable("id") int id);
	
	@RequestMapping(path="/dao/fetch/all-roles",method=RequestMethod.GET)
	public List<Role> findAllRoles() ;

}


