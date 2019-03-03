package com.habbib.sms.feign.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.sms.response.model.Bill;
import com.habbib.sms.response.model.Customerinfo;



@FeignClient(name="db-service",fallback=DBFeignClientFallback.class)
public interface DBServiceFeignClient {
	
	@RequestMapping(path="/dao/fetch/billById/{id}",method=RequestMethod.GET)
	public Optional<Bill> findByBillId(@PathVariable("id") int billId,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/fetch/customerById/{id}", method=RequestMethod.GET)
	public Optional<Customerinfo> findByCustId(@PathVariable("id") int customerId,@RequestParam int shopId);
	
}
