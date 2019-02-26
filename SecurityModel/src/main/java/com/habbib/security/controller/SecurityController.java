/**
 * 
 */
package com.habbib.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.security.feign.client.DBServiceFeignClient;
import com.habbib.security.response.model.Role;
import com.habib.utility.DefaultMessage;

/**
 * @author yash
 *
 */

@RestController
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	private DBServiceFeignClient dbClient;
	
	@RequestMapping(path="/fetch-all-roles",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Role>>> saveRoles() {
		DefaultMessage<List<Role>> defualt = new DefaultMessage<List<Role>>();
		
		List<Role> roleList = dbClient.findAllRoles();
		if(roleList.size() > 0 && roleList != null) {
			defualt.setResponseCode("200");
			defualt.setResponseMessage("Role list found with total count :"+roleList.size());
			defualt.setResponse(roleList);
			ResponseEntity<DefaultMessage<List<Role>>> response = ResponseEntity.ok(defualt);
			return response;
		}
		else {
			defualt.setResponseCode("201");
			defualt.setResponseMessage("role list is empty");
			defualt.setResponse(null);
			ResponseEntity<DefaultMessage<List<Role>>> response = ResponseEntity.ok(defualt);
			return response;
		}
	}
	
}
