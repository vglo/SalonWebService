/**
 * 
 */
package com.habbib.dao.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.UserCrendentialRepository;
import com.habbib.dao.entitiy.Usercredential;
import com.habbib.dao.model.StaffUpdatedCredential;

/**
 * @author yash
 *
 */
@RequestMapping("dao")
@RestController
public class AuthController {

	@Autowired
	private UserCrendentialRepository userRepo;
	
	@RequestMapping(path="/find-credentials",method=RequestMethod.GET)
	public Usercredential findStaffCredentials(@RequestParam String username) {
		
		Optional<Usercredential> user =  userRepo.findByUsername(username);
		if(user.isPresent())
			return user.get();
		return null;
	}
	
	@RequestMapping(path="/check-username",method=RequestMethod.GET)
	public boolean checkUsername(@RequestParam String userName) {
		Optional<Usercredential> user = userRepo.findByUsername(userName);
		if(user.isPresent())
			return true;
		return false;
	}
	
	
	@RequestMapping(path="/change-pass",method=RequestMethod.POST)
	public boolean changePassword(@RequestBody StaffUpdatedCredential newCredentials) {
		
		Usercredential user = new Usercredential();
		user.setUsername(newCredentials.getUsername());
		user.setPassword(newCredentials.getPassword());
		user.setSalt(newCredentials.getSalt());
		
		Usercredential newUSer = userRepo.save(user);
		if(null != newUSer)
			return true;
		return false;
	}
}
