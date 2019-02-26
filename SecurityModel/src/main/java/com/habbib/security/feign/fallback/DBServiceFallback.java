/**
 * 
 */
package com.habbib.security.feign.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.habbib.security.feign.client.DBServiceFeignClient;
import com.habbib.security.response.model.Role;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient{

	@Override
	public void deleteRole(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
