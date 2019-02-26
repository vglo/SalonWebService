package com.habbib.staff.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.staff.feign.clients.DBServiceFeignClient;
import com.habbib.staff.request.model.StaffinfoRequest;
import com.habbib.staff.response.model.Staffinfo;
import com.habbib.staff.util.Utilities;
import com.habib.utility.DefaultMessage;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private DBServiceFeignClient dbFeignClient;
	
	@Autowired
	private Utilities util;

	@RequestMapping(path="/save-staff",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Staffinfo>> saveStaffDetail(@ModelAttribute StaffinfoRequest staffInfoReq) {
		
		if(staffInfoReq == null)
			throw new NullPointerException();
		
		DefaultMessage<Staffinfo> defualt = new DefaultMessage<Staffinfo>();
		//check if customer already exists or not
		Optional<Staffinfo> staffCheck = dbFeignClient.validateStaff(staffInfoReq.getMobile(),staffInfoReq.getShopId());
		
		if(staffCheck.isPresent()) {
			defualt.setResponseCode("201");
			defualt.setResponseMessage("staff not created");
			defualt.setResponse(staffCheck.get());
			ResponseEntity<DefaultMessage<Staffinfo>> response = ResponseEntity.ok(defualt);
			return response;
		}else {
			//to convert date into specific date formate 
			staffInfoReq.setDob(util.convertDateFormate(staffInfoReq.getDob()));
			//saving customer
			Staffinfo newStaff = dbFeignClient.saveStaffInfo(staffInfoReq);
			defualt.setResponseCode("200");
			defualt.setResponseMessage("Staff created successfuly");
			defualt.setResponse(newStaff);
			ResponseEntity<DefaultMessage<Staffinfo>> response = ResponseEntity.ok(defualt);
			return response;
		
		}

	}
	
	@RequestMapping(path="/find-staff/shop-id",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Staffinfo>>> fetchByShopId(@RequestParam int shopId){
	
		DefaultMessage<List<Staffinfo>> defaultResponse = new DefaultMessage<List<Staffinfo>>();
		
		List<Staffinfo> staffList = dbFeignClient.findStaffByShopId(shopId);
		
		if(staffList.size() > 0 && staffList != null) {
			defaultResponse.setResponse(staffList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			 ResponseEntity<DefaultMessage<List<Staffinfo>>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}else {
			defaultResponse.setResponse(staffList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("staff list with given id not found");
			 ResponseEntity<DefaultMessage<List<Staffinfo>>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}
	}
	
	
	@RequestMapping(path="/find-staff/staff-id/{staffId}",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Staffinfo>> fetchStaffByStaffId(@PathVariable int staffId){
		
	DefaultMessage<Staffinfo> defaultResponse = new DefaultMessage<Staffinfo>();
		
		Staffinfo staff = dbFeignClient.findStaffByid(staffId);
		
		if(staff != null) {
			defaultResponse.setResponse(staff);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			 ResponseEntity<DefaultMessage<Staffinfo>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}else {
			defaultResponse.setResponse(staff);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("staff list with given id not found");
			 ResponseEntity<DefaultMessage<Staffinfo>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}
		
	}
}
