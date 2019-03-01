/**
 * 
 */
package com.habbib.pdf.feign.client.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.pdf.feign.client.DBServiceFeignClient;
import com.habbib.pdf.model.Appointment;
import com.habbib.pdf.model.Bill;
import com.habbib.pdf.model.Billhasservice;
import com.habbib.pdf.model.Customerinfo;
import com.habbib.pdf.model.Salonservice;
import com.habbib.pdf.model.Shopinfo;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {


	
	@Override
	public Optional<Customerinfo> findByCustId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Appointment> fetchByCustomerId(int custid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Optional<Shopinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Bill> findByBillId(int billId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Salonservice> getServiceInfo(int serviceId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Billhasservice> fetchBillhasServiceByBillId(int billId) {
		// TODO Auto-generated method stub
		return null;
	}

}
