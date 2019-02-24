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
import com.habbib.customer.response.model.DefaultReponse;
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
	public ResponseEntity<DefaultReponse<Customerinfo>> saveCustomer(@ModelAttribute CustomerRequest customer) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		DefaultReponse<Customerinfo> defualt = new DefaultReponse<Customerinfo>();
		List<Customerinfo> existCust = dbFeignClient.findByCustomerMob(customer.getMobile());
		if( 0 != existCust.size() && null != existCust ) {
			defualt.setResponseCode(201);
			defualt.setResponseMsg("Customer not created");
			defualt.setReponseObj(existCust.get(0));
			ResponseEntity<DefaultReponse<Customerinfo>> response = ResponseEntity.ok(defualt);
			return response;
		}else {
			//to convert date into specific date formate 
			customer.setDob(util.convertDateFormate(customer.getDob()));
			//saving customer
			Customerinfo newCust = dbFeignClient.saveCustomer(customer);
			defualt.setResponseCode(200);
			defualt.setResponseMsg("Customer created successfuly");
			defualt.setReponseObj(newCust);
			ResponseEntity<DefaultReponse<Customerinfo>> response = ResponseEntity.ok(defualt);
			return response;
		
		}
		
		
	}

	
	@RequestMapping(path="/update-customer", method=RequestMethod.PUT)
	public ResponseEntity<DefaultReponse<Customerinfo>> updateCustomer(@ModelAttribute Customerinfo customer) {
		DefaultReponse<Customerinfo> defualt = new DefaultReponse<Customerinfo>();
		if(null != customer)
		 return ResponseEntity.ok(defualt);
		Customerinfo newCust = dbFeignClient.uddateCustomer(customer);
		defualt.setResponseCode(200);
		defualt.setResponseMsg("Customer updated successfuly");
		defualt.setReponseObj(newCust);
		ResponseEntity<DefaultReponse<Customerinfo>> response = ResponseEntity.ok(defualt);
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
	public ResponseEntity<DefaultReponse<List<Customerinfo>>> fetchAllCustomerByShopId(@RequestParam(value="shopId", required=true) int shopId){
		DefaultReponse<List<Customerinfo>> defaultResponse = new DefaultReponse<List<Customerinfo>>();
		
		List<Customerinfo> customerList = dbFeignClient.findByShopId(shopId);
		
		if(customerList.size() > 0 && customerList != null) {
			defaultResponse.setReponseObj(customerList);
			defaultResponse.setResponseCode(200);
			defaultResponse.setResponseMsg("Please find the customer list with given shop id");
			 ResponseEntity<DefaultReponse<List<Customerinfo>>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}else {
			defaultResponse.setReponseObj(customerList);
			defaultResponse.setResponseCode(200);
			defaultResponse.setResponseMsg("customer list with given id not found");
			 ResponseEntity<DefaultReponse<List<Customerinfo>>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}
	}
	
	
	@RequestMapping(path="/fetch-customer/{custId}", method=RequestMethod.GET)
	public ResponseEntity<DefaultReponse<Customerinfo>> fetchAllCustomerById(@PathVariable int custId){
		DefaultReponse<Customerinfo> defaultResponse = new DefaultReponse<Customerinfo>();
		
		Optional<Customerinfo> customerList = dbFeignClient.findByCustId(custId);
		if(customerList.isPresent()) {
			defaultResponse.setReponseObj(customerList.get());
			defaultResponse.setResponseCode(200);
			defaultResponse.setResponseMsg("Please find the customer");
			 ResponseEntity<DefaultReponse<Customerinfo>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}else {
			defaultResponse.setReponseObj(customerList.get());
			defaultResponse.setResponseCode(200);
			defaultResponse.setResponseMsg("customer with given id not found");
			 ResponseEntity<DefaultReponse<Customerinfo>> responseEntity = ResponseEntity.ok(defaultResponse);
			 return responseEntity;
		}
		
	}
	
	@RequestMapping(path="/create-appoitment",method=RequestMethod.POST)
	public ResponseEntity<DefaultReponse<Appointment>> createAppoitment(@ModelAttribute AppointmentRequest appoitmentRequest){
		DefaultReponse<Appointment> defaultResponse = new DefaultReponse<Appointment>();
		Appointment appoitment = dbFeignClient.saveAppointment(appoitmentRequest);
		defaultResponse.setReponseObj(appoitment);
		defaultResponse.setResponseCode(200);
		defaultResponse.setResponseMsg("Appoitment saved successfully");
		ResponseEntity<DefaultReponse<Appointment>> response = ResponseEntity.ok(defaultResponse);
		return response;
	}
	
	
}
