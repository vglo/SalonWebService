/**
 * 
 */
package com.habbib.customer.feign.client.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.customer.feign.client.DBServiceFeignClient;
import com.habbib.customer.request.model.AppointmentRequest;
import com.habbib.customer.request.model.CustomerRequest;
import com.habbib.customer.response.model.Appointment;
import com.habbib.customer.response.model.Customerinfo;
import com.habbib.customer.response.model.Shopinfo;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {

	@Override
	public Customerinfo saveCustomer(CustomerRequest cust) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customerinfo> findAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customerinfo> findByCustId(int customerId, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<Customerinfo> findCustByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Appointment> fetchAppointmentByshopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> fetchByCustomerId(int custid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Appointment> fetchAllAppoitment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment saveAppointment(AppointmentRequest appointment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customerinfo> validateCust(String mobileNum, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customerinfo> findCustbyEmail(String email, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Shopinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customerinfo updateCustomer(CustomerRequest cust, int custId) {
		// TODO Auto-generated method stub
		return null;
	}

}
