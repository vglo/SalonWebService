package com.habbib.staff.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.staff.feign.clients.DBServiceFeignClient;
import com.habbib.staff.request.model.StaffUpdatedCredential;
import com.habbib.staff.request.model.StaffinfoRequest;
import com.habbib.staff.response.model.Role;
import com.habbib.staff.response.model.Shopinfo;
import com.habbib.staff.response.model.Staffinfo;
import com.habbib.staff.response.model.Usercredential;
import com.habbib.staff.service.StaffService;
import com.habbib.staff.util.Utilities;
import com.habib.utility.DefaultMessage;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private DBServiceFeignClient dbFeignClient;
	
	@Autowired
	private StaffService service;
	
	@Autowired
	private Utilities util;
	

	@RequestMapping(path="/save-staff",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Staffinfo>> saveStaffDetail(@Valid @ModelAttribute StaffinfoRequest staffInfoReq) {

		DefaultMessage<Staffinfo> defualt = new DefaultMessage<Staffinfo>();
		if(staffInfoReq == null)
			throw new NullPointerException();
		try {
		Optional<Shopinfo> shopInfo = dbFeignClient.findByShopId(staffInfoReq.getShopId());
		if(!shopInfo.isPresent()) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
		}
		List<Role> role = dbFeignClient.findRoleListByIds(staffInfoReq.getRoleId());
		if(null == role) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Role with given id is not present/registered, please enter valid Role id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
		}
		
		//check if customer already exists or not
		Optional<Staffinfo> staffCheck = dbFeignClient.validateStaff(staffInfoReq.getMobile(),staffInfoReq.getShopId());
		
		if(staffCheck.isPresent()) {
			defualt.setResponseCode("302");
			defualt.setResponseMessage("staff already present with id:"+staffCheck.get().getIdStaffInfo());
			defualt.setResponse(null);
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.FOUND);
		}else {
			//to convert date into specific date formate 
			staffInfoReq.setDob(util.convertDateFormate(staffInfoReq.getDob()));
			//saving customer
			Staffinfo newStaff = dbFeignClient.saveStaffInfo(staffInfoReq);
			defualt.setResponseCode("201");
			defualt.setResponseMessage("Staff created successfuly");
			defualt.setResponse(newStaff);
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.OK);
		
		}
	}catch (Exception e) {
		defualt.setResponse(null);
		defualt.setResponseCode("500");
		defualt.setResponseMessage("error occured"+e.getLocalizedMessage());
		return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@RequestMapping(path="/find-staff/shop-id",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Staffinfo>>> fetchByShopId(@RequestParam int shopId){
	
		DefaultMessage<List<Staffinfo>> defaultResponse = new DefaultMessage<List<Staffinfo>>();
		
		List<Staffinfo> staffList = dbFeignClient.findStaffByShopId(shopId);
		
		if(staffList.size() > 0 && staffList != null) {
			defaultResponse.setResponse(staffList);
			defaultResponse.setResponseCode("302");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			return new ResponseEntity<DefaultMessage<List<Staffinfo>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(staffList);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("staff list with given id is empty");
			return new  ResponseEntity<DefaultMessage<List<Staffinfo>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/find-staff/staff-id",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Staffinfo>> fetchStaffByStaffId(@RequestParam int staffId,@RequestParam int shopId){
		
	DefaultMessage<Staffinfo> defaultResponse = new DefaultMessage<Staffinfo>();
		
		Staffinfo staff = dbFeignClient.findStaffByid(staffId, shopId);
		
		if(staff != null) {
			defaultResponse.setResponse(staff);
			defaultResponse.setResponseCode("302");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(staff);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("staff list with given id not found");
			return new  ResponseEntity<DefaultMessage<Staffinfo>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(path="/find-staff/role",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Staffinfo>>> findStaffsByRole(@RequestParam(required=true) int roleId,@RequestParam(required=true) int shopId){
		DefaultMessage<List<Staffinfo>> defaultResponse = new DefaultMessage<List<Staffinfo>>();
		
		List<Staffinfo> staffList = dbFeignClient.findStaffByRole(roleId, shopId);
		
		if(staffList.size() > 0 && staffList != null) {
			defaultResponse.setResponse(staffList);
			defaultResponse.setResponseCode("302");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			return new ResponseEntity<DefaultMessage<List<Staffinfo>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(staffList);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("staff list with given id is empty");
			return new  ResponseEntity<DefaultMessage<List<Staffinfo>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path="/find-staff/mobile",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Staffinfo>> findStaffByMobile(@RequestParam(required=true) String mobile,@RequestParam(required=true) int shopId){
		DefaultMessage<Staffinfo> defaultResponse = new DefaultMessage<Staffinfo>();
		
		Optional<Staffinfo> staff = dbFeignClient.validateStaff(mobile, shopId);
		
		if(staff.isPresent()) {
			defaultResponse.setResponse(staff.get());
			defaultResponse.setResponseCode("302");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(staff.get());
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("staff list with given id not found");
			return new  ResponseEntity<DefaultMessage<Staffinfo>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/find-staff/email",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Staffinfo>> findStaffByEmail(@Email @RequestParam(required=true) String email,@RequestParam(required=true) int shopId){
		DefaultMessage<Staffinfo> defaultResponse = new DefaultMessage<Staffinfo>();
		Staffinfo staff = dbFeignClient.findStaffByEmail(email, shopId);
		
		if(staff != null) {
			defaultResponse.setResponse(staff);
			defaultResponse.setResponseCode("302");
			defaultResponse.setResponseMessage("Please find the staff list with given shop id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(staff);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("staff list with given id not found");
			return new  ResponseEntity<DefaultMessage<Staffinfo>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(path="/validate-staff",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Staffinfo>> validateStaff(@RequestParam String username,@RequestParam String password) throws NoSuchAlgorithmException{
		
		
		if(username == null && password == null)
			return new ResponseEntity<DefaultMessage<Staffinfo>>(HttpStatus.BAD_REQUEST);
		
		DefaultMessage<Staffinfo> defaultmsg = new DefaultMessage<Staffinfo>();
		
		Staffinfo staff =  dbFeignClient.findStaffByUsername(username, password);
		if(null != staff) {
			Usercredential user = dbFeignClient.findStaffCredentials(username);
			if(service.checkHash(password, user.getPassword(),user.getSalt())) {
				defaultmsg.setResponseCode("200");
				defaultmsg.setResponseMessage("staff authenticated successfully:  userName:"+user.getUsername());
				defaultmsg.setResponse(staff);
				return new ResponseEntity<DefaultMessage<Staffinfo>>(defaultmsg,HttpStatus.OK);
			}else {
				defaultmsg.setResponseCode("400");
				defaultmsg.setResponseMessage("password entered is wrong with given username: "+user.getUsername());
				defaultmsg.setResponse(null);
				return new ResponseEntity<DefaultMessage<Staffinfo>>(defaultmsg,HttpStatus.BAD_REQUEST);
			}
			
		}else {
			defaultmsg.setResponseCode("400");
			defaultmsg.setResponseMessage("staff not present with given userId: "+username);
			defaultmsg.setResponse(staff);
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defaultmsg,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(path="/update-staff",method=RequestMethod.PUT)
	public ResponseEntity<DefaultMessage<Staffinfo>> updateStaff(@Valid @ModelAttribute StaffinfoRequest staffInfoReq,@RequestParam(required=true) int staffId){


		DefaultMessage<Staffinfo> defualt = new DefaultMessage<Staffinfo>();
		if(staffInfoReq == null)
			throw new NullPointerException();
		try {
		Optional<Shopinfo> shopInfo = dbFeignClient.findByShopId(staffInfoReq.getShopId());
		if(!shopInfo.isPresent()) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
		}
		List<Role> role = dbFeignClient.findRoleListByIds(staffInfoReq.getRoleId());
		if(null == role) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Role with given id is not present/registered, please enter valid Role id");
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.BAD_REQUEST);
		}
		
		//check if customer already exists or not
		Staffinfo staffCheck = dbFeignClient.findStaffByid(staffId, staffInfoReq.getShopId());
		
		if(null != staffCheck) {
			Staffinfo newStaff = dbFeignClient.updateStaff(staffInfoReq,staffId);
			defualt.setResponseCode("200");
			defualt.setResponseMessage("staff updated with staff id:"+staffCheck.getIdStaffInfo());
			defualt.setResponse(newStaff);
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.OK);
		}else {
			defualt.setResponseCode("200");
			defualt.setResponseMessage("Staff not updated:");
			defualt.setResponse(staffCheck);
			return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.OK);
		
		}
	}catch (Exception e) {
		defualt.setResponse(null);
		defualt.setResponseCode("500");
		defualt.setResponseMessage("error occured"+e.getLocalizedMessage());
		return new ResponseEntity<DefaultMessage<Staffinfo>>(defualt,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	
	@RequestMapping(path="/delete-staff",method=RequestMethod.DELETE)
	public ResponseEntity<DefaultMessage<Boolean>> deleteStaff(@RequestParam int staffId,@RequestParam int shopId){
		DefaultMessage<Boolean> defaultMsg= new DefaultMessage<Boolean>();
		try {
			dbFeignClient.deleteStaff(staffId, shopId);
		}catch (Exception e) {
			defaultMsg.setResponse(false);
			defaultMsg.setResponseCode("400");
			defaultMsg.setResponseMessage("error occured in delete the staff"+e.getLocalizedMessage());
			return new ResponseEntity<DefaultMessage<Boolean>>(defaultMsg,HttpStatus.BAD_REQUEST);
		}
		defaultMsg.setResponse(true);
		defaultMsg.setResponseCode("200");
		defaultMsg.setResponseMessage("staff deleted with given id");
		return new ResponseEntity<DefaultMessage<Boolean>>(defaultMsg,HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/change-password",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<String>> changePassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String username) throws NoSuchAlgorithmException{
		DefaultMessage<String> defaultmsg = new DefaultMessage<String>();
		try {
			Usercredential user = dbFeignClient.findStaffCredentials(username);
			if(service.checkHash(oldPassword, user.getPassword(),user.getSalt())) {
				String salt= service.getSalt();
				StaffUpdatedCredential updatedCredential = new StaffUpdatedCredential();
				updatedCredential.setPassword(service.generatePass(newPassword, salt));
				updatedCredential.setSalt(salt);
				updatedCredential.setUsername(username);
				boolean check = dbFeignClient.changePassword(updatedCredential);
				defaultmsg.setResponseCode("200");
				defaultmsg.setResponseMessage("");
				defaultmsg.setResponse("Password changed successfully");
				return new ResponseEntity<DefaultMessage<String>>(defaultmsg,HttpStatus.OK);
			}else {
				defaultmsg.setResponseCode("400");
				defaultmsg.setResponseMessage("password entered is wrong with given username: "+user.getUsername());
				defaultmsg.setResponse("Old password is not matched with user name");
				return new ResponseEntity<DefaultMessage<String>>(defaultmsg,HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			defaultmsg.setResponse("Error ocured");
			defaultmsg.setResponseCode("500");
			defaultmsg.setResponseMessage("error occured"+e.getLocalizedMessage());
			return new ResponseEntity<DefaultMessage<String>>(defaultmsg,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

