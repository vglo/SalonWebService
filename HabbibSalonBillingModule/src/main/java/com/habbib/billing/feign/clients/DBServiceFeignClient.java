package com.habbib.billing.feign.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.habbib.billing.feign.client.fallback.DBServiceFallback;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Customerinfo;
import com.habbib.billing.model.Salonservice;
import com.habbib.billing.response.model.BillResponse;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {

	@RequestMapping(value="/dao/save-bill")
	public void  saveBill(@RequestBody BillResponse bill);
	
	@RequestMapping(value="/dao/find/all-bills")
	public List<Bill> findAllBills();
	
	@RequestMapping(value="/dao/delete-bill/{id}")
	public void deleteBill(@PathVariable("id") int id );
	
	@RequestMapping(value="/dao/update-bill")
	public void updateBill(@RequestBody Bill bill);
	
	
	@RequestMapping(path="/dao/fetch/billById/{id}")
	public Optional<Bill> findByBillId(@PathVariable("id") int billId);
	
	@RequestMapping(path="/dao/fetch/billByBillNum/{bill-number}")
	public Optional<Bill> findByBillNum(@PathVariable("bill-number") String billNumber);
	
	@RequestMapping(path="/dao/salon-service/{shopId}")
	public List<Salonservice> getSalonServices(@PathVariable int shopId);
	
	@RequestMapping(path="/dao/get-service-info/{serviceId}",method=RequestMethod.GET)
	public Optional<Salonservice> getServiceInfo(@PathVariable int serviceId);

	@RequestMapping(path="/dao/find-by-shop-id/{shopId}", method=RequestMethod.GET)
	public List<Customerinfo> findByShopId(@PathVariable int shopId);
	
	@RequestMapping(path="/dao/fetch/customerById/{id}")
	public Optional<Customerinfo> findByCustId(@PathVariable("id") int customerId);
	
}
