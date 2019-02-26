package com.habbib.billing.feign.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.billing.dbrequest.model.BillRequest;
import com.habbib.billing.feign.client.fallback.DBServiceFallback;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Customerinfo;
import com.habbib.billing.model.Paymenttype;
import com.habbib.billing.model.Salonservice;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {

	@RequestMapping(value="/dao/save-bill", method=RequestMethod.POST)
	public Bill saveBill(@RequestBody BillRequest billModel);
	
	@RequestMapping(value="/dao/find/all-bills",method=RequestMethod.GET)
	public List<Bill> findAllBills();
	
	@RequestMapping(value="/dao/delete-bill/{id}",method=RequestMethod.DELETE)
	public void deleteBill(@PathVariable("id") int id );
	
	@RequestMapping(value="/dao/update-bill",method=RequestMethod.PUT)
	public void updateBill(@RequestBody Bill bill);
	
	
	@RequestMapping(path="/dao/fetch/billById/{id}",method=RequestMethod.GET)
	public Optional<Bill> findByBillId(@PathVariable("id") int billId);
	
	@RequestMapping(path="/dao/fetch/billByBillNum/{bill-number}",method=RequestMethod.GET)
	public Optional<Bill> findByBillNum(@PathVariable("bill-number") String billNumber);
	
	@RequestMapping(path="/dao/salon-service/{shopId}",method=RequestMethod.GET)
	public List<Salonservice> getSalonServices(@PathVariable int shopId);
	
	@RequestMapping(path="/dao/get-service-info/{serviceId}",method=RequestMethod.GET)
	public Optional<Salonservice> getServiceInfo(@PathVariable int serviceId);

	@RequestMapping(path="/dao/find-by-shop-id/{shopId}", method=RequestMethod.GET)
	public List<Customerinfo> findByShopId(@PathVariable int shopId);
	
	@RequestMapping(path="/dao/fetch/customerById/{id}",method=RequestMethod.GET)
	public Optional<Customerinfo> findByCustId(@PathVariable("id") int customerId);
	
	@RequestMapping(path="/dao/filter-date-range",method=RequestMethod.GET)
	public List<Bill> filterByDateRange(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/filter-date",method=RequestMethod.GET)
	public List<Bill> filterByDate(@RequestParam int shopId);
	
	@RequestMapping(path="/dao/fetch-payment-types",method=RequestMethod.GET)
	public List<Paymenttype> fetchAllPaymentType();
	
	@RequestMapping(path="/dao/find-bill/shop-id")
	public List<Bill> findBillByShopId(@RequestParam int shopId);
	
}
