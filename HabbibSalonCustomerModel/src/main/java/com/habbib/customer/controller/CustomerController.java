package com.habbib.customer.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.habib.utility.DefaultMessage;
import com.habbib.customer.util.Utilities;

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
		List<Customerinfo> existCust = dbFeignClient.findByCustomerMob(customer.getMobile());
		if( 0 != existCust.size() && null != existCust ) {
			defualt.setResponseCode("201");
			defualt.setResponseMessage("Customer not created");
			defualt.setResponse(existCust.get(0));
			ResponseEntity<DefaultMessage<Customerinfo>> response = ResponseEntity.ok(defualt);
			return response;
		}else {
			//to convert date into specific date formate 
			customer.setDob(util.convertDateFormate(customer.getDob()));
			//saving customer
			Customerinfo newCust = dbFeignClient.saveCustomer(customer);
			defualt.setResponseCode("200");
			defualt.setResponseMessage("Customer created successfuly");
			defualt.setResponse(newCust);
			ResponseEntity<DefaultMessage<Customerinfo>> response = ResponseEntity.ok(defualt);
			return response;
		
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
		
		List<Customerinfo> customerList = dbFeignClient.findByShopId(shopId);
		
		if(customerList.size() > 0 && customerList != null) {
			defaultResponse.setResponse(customerList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the customer list with given shop id");
			 ResponseEntity<DefaultMessage<List<Customerinfo>>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}else {
			defaultResponse.setResponse(customerList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("customer list with given id not found");
			 ResponseEntity<DefaultMessage<List<Customerinfo>>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}
	}
	
	
	@RequestMapping(path="/fetch-customer/{custId}", method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Customerinfo>> fetchAllCustomerById(@PathVariable int custId){
		DefaultMessage<Customerinfo> defaultResponse = new DefaultMessage<Customerinfo>();
		
		Optional<Customerinfo> customerList = dbFeignClient.findByCustId(custId);
		if(customerList.isPresent()) {
			defaultResponse.setResponse(customerList.get());
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the customer");
			 ResponseEntity<DefaultMessage<Customerinfo>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}else {
			defaultResponse.setResponse(customerList.get());
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("customer with given id not found");
			 ResponseEntity<DefaultMessage<Customerinfo>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}
		
	}
	
	@RequestMapping(path="/create-appoitment",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Appointment>> createAppoitment(@ModelAttribute AppointmentRequest appoitmentRequest){
		DefaultMessage<Appointment> defaultResponse = new DefaultMessage<Appointment>();
		Appointment appoitment = dbFeignClient.saveAppointment(appoitmentRequest);
		defaultResponse.setResponse(appoitment);
		defaultResponse.setResponseCode("200");
		defaultResponse.setResponseMessage("Appoitment saved successfully");
		ResponseEntity<DefaultMessage<Appointment>> response = ResponseEntity.ok(defaultResponse);
		return response;
	}
	
	
}
