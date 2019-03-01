package com.habbib.service.dbfeign.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.service.feign.fallback.DBServiceFallback;
import com.habbib.service.request.model.SalonserviceRequest;
import com.habbib.service.response.model.Salonservice;
import com.habbib.service.response.model.Shopinfo;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {

	@RequestMapping(path="/dao/fetch/shopById/{shopId}",method=RequestMethod.GET)
	public Optional<Shopinfo> findByShopId(@PathVariable() int shopId);
	
	@RequestMapping(path="/dao/salon-service/{shopId}",method=RequestMethod.GET)
	public List<Salonservice> getSalonServices(@PathVariable int shopId);
	
	@RequestMapping(path="/dao/get-service-info/{serviceId}",method=RequestMethod.GET)
	public Optional<Salonservice> getServiceInfo(@PathVariable int serviceId);
	
	
	@RequestMapping(path="/dao/save-service",method=RequestMethod.POST)
	public Salonservice saveService(@RequestBody SalonserviceRequest salonServiceReq);
	
	@RequestMapping(path="/dao/validate-service",method=RequestMethod.GET)
	public Salonservice validateService(@RequestParam String name,@RequestParam double price,@RequestParam int shopId);
	
	
}
