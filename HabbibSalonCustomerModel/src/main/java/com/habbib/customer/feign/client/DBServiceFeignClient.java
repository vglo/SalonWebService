package com.habbib.customer.feign.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.customer.feign.client.fallback.DBServiceFallback;
import com.habbib.customer.request.model.AppointmentRequest;
import com.habbib.customer.request.model.CustomerRequest;
import com.habbib.customer.response.model.Appointment;
import com.habbib.customer.response.model.Customerinfo;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {


	@RequestMapping(path="/dao/save-customer",method=RequestMethod.POST)
	public Customerinfo saveCustomer(@RequestBody CustomerRequest cust);
	
	@RequestMapping(path="/dao/delete-customer/{id}", method=RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable("id") int id);
	
	@RequestMapping(path="/dao/fetch/all-customer", method=RequestMethod.GET)
	public List<Customerinfo> findAllCustomer();
	
	@RequestMapping(path="/dao/fetch/customerById/{id}", method=RequestMethod.GET)
	public Optional<Customerinfo> findByCustId(@PathVariable("id") int customerId);
	
	@RequestMapping(path="/dao/update-customer",method=RequestMethod.PUT)
	public Customerinfo uddateCustomer(@RequestBody Customerinfo customer);
	
	@RequestMapping(path="/dao/find-by-shop-id/{shopId}", method=RequestMethod.GET)
	public List<Customerinfo> findByShopId(@PathVariable int shopId);
	
	@RequestMapping(path="/dao/find-by-mobile/{mobileNum}",method=RequestMethod.GET)
	public List<Customerinfo> findByCustomerMob(@PathVariable String mobileNum);
	
	@RequestMapping(path="/dao/fetch-appoitment/shop-id",method=RequestMethod.GET)
	public List<Appointment> fetchAppointmentByshopId(@RequestParam int shopId);
	
	@RequestMapping(path="/dao/find-appointment/custmer-id",method=RequestMethod.GET)
	public List<Appointment> fetchByCustomerId(@RequestParam int custid);
	
	@RequestMapping(path="/dao/create-appointment",method=RequestMethod.POST)
	public Appointment saveAppointment(@RequestBody AppointmentRequest appointment);
	
	@RequestMapping(path="/dao/fetch-all",method=RequestMethod.GET)
	public List<Appointment> fetchAllAppoitment();
	
	
}
