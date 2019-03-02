package com.habbib.customer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.customer.feign.client.DBServiceFeignClient;
import com.habbib.customer.request.model.AppointmentRequest;
import com.habbib.customer.request.model.CustomerRequest;
import com.habbib.customer.response.model.Appointment;
import com.habbib.customer.response.model.Customerinfo;
import com.habbib.customer.response.model.Shopinfo;
import com.habbib.customer.util.Utilities;
import com.habib.utility.DefaultMessage;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private DBServiceFeignClient dbFeignClient;
	
	@Autowired
	private Utilities util;
	
	@RequestMapping(path="/save-customer", method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Customerinfo>> saveCustomer(@ModelAttribute CustomerRequest customer) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		DefaultMessage<Customerinfo> defualt = new DefaultMessage<Customerinfo>();
		if(customer == null)
			throw new NullPointerException();
		Optional<Shopinfo> shopInfo = dbFeignClient.findByShopId(customer.getIdShopInfo());
		if(!shopInfo.isPresent()) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defualt,HttpStatus.BAD_REQUEST);
		}
		
		
		//check if customer already exists or not
		Optional<Customerinfo> custExists = dbFeignClient.validateCust(customer.getMobile(),customer.getIdShopInfo());
		
		if(custExists.isPresent()) {
			defualt.setResponseCode("302");
			defualt.setResponseMessage("Customer already registered");
			defualt.setResponse(custExists.get());
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defualt,HttpStatus.FOUND);
		}else {
			//to convert date into specific date formate 
			customer.setDob(util.convertDateFormate(customer.getDob()));
			//saving customer
			Customerinfo newCust = dbFeignClient.saveCustomer(customer);
			defualt.setResponseCode("200");
			defualt.setResponseMessage("Customer registered successfuly");
			defualt.setResponse(newCust);
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defualt,HttpStatus.OK);
		}
	}

	
	@RequestMapping(path="/update-customer", method=RequestMethod.PUT)
	public ResponseEntity<DefaultMessage<Customerinfo>> updateCustomer(@ModelAttribute Customerinfo customer) {
		DefaultMessage<Customerinfo> defualt = new DefaultMessage<Customerinfo>();
		if(null != customer)
		 return ResponseEntity.ok(defualt);
		Customerinfo newCust = dbFeignClient.uddateCustomer(customer);
		defualt.setResponseCode("200");
		defualt.setResponseMessage("Customer updated successfuly");
		defualt.setResponse(newCust);
		ResponseEntity<DefaultMessage<Customerinfo>> response = ResponseEntity.ok(defualt);
		return response;
	
	}
	
	@RequestMapping(path="/delete-customer/{customerId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
		if(0 != customerId)
		 return ResponseEntity.ok("CustomerId is null");
		dbFeignClient.deleteCustomer(customerId);
		return ResponseEntity.ok("Customer deleted successfully");
	}
	
	@RequestMapping(path="/fetch-customer/shop-id", method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Customerinfo>>> fetchAllCustomerByShopId(@RequestParam(value="shopId", required=true) int shopId){
		DefaultMessage<List<Customerinfo>> defaultResponse = new DefaultMessage<List<Customerinfo>>();
		
		Optional<Shopinfo> shopInfo = dbFeignClient.findByShopId(shopId);
		if(!shopInfo.isPresent()) {
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<List<Customerinfo>>>(defaultResponse,HttpStatus.BAD_REQUEST);
		}
		
		List<Customerinfo> customerList = dbFeignClient.findCustByShopId(shopId);
		
		if(customerList.size() > 0 && customerList != null) {
			defaultResponse.setResponse(customerList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the customer list with given shop id");
			return new ResponseEntity<DefaultMessage<List<Customerinfo>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(customerList);
			defaultResponse.setResponseCode("302");
			defaultResponse.setResponseMessage("customer list with given id not found");
			return new  ResponseEntity<DefaultMessage<List<Customerinfo>>>(defaultResponse,HttpStatus.NO_CONTENT);
		}
	}
	
	
	@RequestMapping(path="/fetch-customer/{custId}", method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Customerinfo>> fetchCustomerById(@PathVariable int custId){
		DefaultMessage<Customerinfo> defaultResponse = new DefaultMessage<Customerinfo>();
		
		Optional<Customerinfo> customerList = dbFeignClient.findByCustId(custId);
		if(customerList.isPresent()) {
			defaultResponse.setResponse(customerList.get());
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the customer");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(customerList.get());
			defaultResponse.setResponseCode("204");
			defaultResponse.setResponseMessage("customer with given id not found");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defaultResponse,HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(path="/create-appoitment",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Appointment>> createAppoitment(@Valid @ModelAttribute AppointmentRequest appoitmentRequest){
		DefaultMessage<Appointment> defaultResponse = new DefaultMessage<Appointment>();
		Appointment appoitment = dbFeignClient.saveAppointment(appoitmentRequest);
		if(null != appoitment) {
			defaultResponse.setResponse(appoitment);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Appoitment saved successfully");
			return new ResponseEntity<DefaultMessage<Appointment>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(appoitment);
			defaultResponse.setResponseCode("400");
			defaultResponse.setResponseMessage("Appoitment saved successfully");
			return new ResponseEntity<DefaultMessage<Appointment>>(defaultResponse,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path="/find-cust/shop-mob",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Customerinfo>> findCustByShopAndMob(@RequestParam(required=true) int shopId,@RequestParam(required=true) String mobileNum){

		DefaultMessage<Customerinfo> defaultResponse = new DefaultMessage<Customerinfo>();
		
		Optional<Customerinfo> customerList = dbFeignClient.validateCust(mobileNum, shopId);
		if(customerList.isPresent()) {
			defaultResponse.setResponse(customerList.get());
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the customer");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(null);
			defaultResponse.setResponseCode("204");
			defaultResponse.setResponseMessage("customer with given mobile number not found");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(path="/find-cust/email",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Customerinfo>> findCustByEmail(@RequestParam(required=true) int shopId,@Email @RequestParam(required=true) String emailId){
		
		DefaultMessage<Customerinfo> defaultResponse = new DefaultMessage<Customerinfo>();
		
		Optional<Customerinfo> customerList = dbFeignClient.findCustbyEmail(emailId, shopId);
		if(customerList.isPresent()) {
			defaultResponse.setResponse(customerList.get());
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the customer");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(null);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("customer with given email id not found");
			return new ResponseEntity<DefaultMessage<Customerinfo>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}
