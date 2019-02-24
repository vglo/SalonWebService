/**
 * 
 */
package com.habbib.customer.feign.client.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.customer.feign.client.DBServiceFeignClient;
import com.habbib.customer.request.model.AppointmentRequest;
import com.habbib.customer.request.model.CustomerRequest;
import com.habbib.customer.response.model.Appointment;
import com.habbib.customer.response.model.Customerinfo;

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
	public Optional<Customerinfo> findByCustId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customerinfo uddateCustomer(Customerinfo customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customerinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customerinfo> findByCustomerMob(String mobileNum) {
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

}
