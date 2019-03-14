package com.habbib.staff.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.staff.config.PasswordEncoder;
import com.habbib.staff.feign.clients.DBServiceFeignClient;
import com.habbib.staff.request.model.StaffCrendentialRequest;
import com.habbib.staff.response.model.Role;
import com.habbib.staff.response.model.Shopinfo;
import com.habbib.staff.response.model.Staffinfo;
import com.habbib.staff.service.StaffService;
import com.habbib.staff.util.Utilities;
import com.habib.utility.DefaultMessage;

@RestController
@RequestMapping("register")
public class StaffRegisterController {

	@Autowired
	private DBServiceFeignClient dbFeignClient;
	
	@Autowired
	private StaffService service;
	
	@Autowired
	private Utilities util;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@RequestMapping(path="/register-staff",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Staffinfo>> saveAndRegister(@ModelAttribute StaffCrendentialRequest staffCredential){
		if(null == staffCredential.getUserName())
			return new ResponseEntity<DefaultMessage<Staffinfo>>(HttpStatus.BAD_REQUEST);
		
		DefaultMessage<Staffinfo> defualt = new DefaultMessage<Staffinfo>();
		try {
			if(!dbFeignClient.checkUsername(staffCredential.getUserName())) {
				Optional<Shopinfo> shopInfo = dbFeignClient.findByShopId(staffCredential.getShopId());
				if(!shopInfo.isPresent()) {
					defualt.setResponseCode("404");
					defualt.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
					return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
				}
				List<Role> role = dbFeignClient.findRoleListByIds(staffCredential.getRoleId());
				if(null == role) {
					defualt.setResponseCode("404");
					defualt.setResponseMessage("Role with given id is not present/registered, please enter valid Role id");
					return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
				}
				//check if customer already exists or not
				Optional<Staffinfo> staffCheck = dbFeignClient.validateStaff(staffCredential.getMobile(),staffCredential.getShopId());
				if(staffCheck.isPresent()) {
					defualt.setResponseCode("302");
					defualt.setResponseMessage("staff already present with id:"+staffCheck.get().getIdStaffInfo());
					defualt.setResponse(null);
					return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.FOUND);
				}else {
					//saving customer
					/*
					 * String salt = PasswordEncoder.getSalt(); staffCredential =
					 * service.generateCredential(staffCredential, salt);
					 * staffCredential.setDob(util.convertDateFormate(staffCredential.getDob()));
					 * Staffinfo newStaff = dbFeignClient.registerStaffInfo(staffCredential,salt);
					 */
					defualt.setResponseCode("201");
					defualt.setResponseMessage("Staff saved successfuly, with username");
					defualt.setResponse(null);
					
					return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.OK);
				
				}
			}else {
				defualt.setResponseCode("400");
				defualt.setResponseMessage("Username already exists");
				defualt.setResponse(null);
				return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
			}
			
			
		}catch(Exception e) {
			defualt.setResponse(null);
			defualt.setResponseCode("500");
			defualt.setResponseMessage("error occured"+e.getLocalizedMessage());
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
