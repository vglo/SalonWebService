package com.habbib.pdf.feign.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.pdf.feign.client.fallback.DBServiceFallback;
import com.habbib.pdf.model.Appointment;
import com.habbib.pdf.model.Bill;
import com.habbib.pdf.model.Billhasservice;
import com.habbib.pdf.model.Customerinfo;
import com.habbib.pdf.model.Salonservice;
import com.habbib.pdf.model.Shopinfo;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {

	@RequestMapping(path="/dao/fetch/customerById/{id}", method=RequestMethod.GET)
	public Optional<Customerinfo> findByCustId(@PathVariable("id") int customerId);
	
	@RequestMapping(path="/dao/find-appointment/custmer-id",method=RequestMethod.GET)
	public List<Appointment> fetchByCustomerId(@RequestParam int custid);
	
	@RequestMapping(path="/dao/fetch/shopById/{shopId}",method=RequestMethod.GET)
	public Optional<Shopinfo> findByShopId(@PathVariable() int shopId);
	
	@RequestMapping(path="/dao/fetch/billById/{id}", method=RequestMethod.GET)
	public Optional<Bill> findByBillId(@PathVariable("id") int billId);
	
	@RequestMapping(path="/dao/get-service-info/{serviceId}",method=RequestMethod.GET)
	public Optional<Salonservice> getServiceInfo(@PathVariable int serviceId);
	
	@RequestMapping(path="/dao/find-billhasservice/bill-id",method=RequestMethod.GET)
	public List<Billhasservice> fetchBillhasServiceByBillId(@RequestParam int billId);
}
