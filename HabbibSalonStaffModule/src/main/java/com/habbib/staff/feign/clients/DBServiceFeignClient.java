package com.habbib.staff.feign.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.staff.feign.client.fallback.DBServiceFallback;
import com.habbib.staff.request.model.StaffinfoRequest;
import com.habbib.staff.response.model.Staffinfo;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {
	
	@RequestMapping(path="/dao/find-staff/mobile&shopId",method=RequestMethod.GET)
	public Optional<Staffinfo> validateStaff(@RequestParam String mobileNum,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/save-staff",method=RequestMethod.POST)
	public Staffinfo saveStaffInfo(@RequestBody StaffinfoRequest staffInfoReq);
	
	@RequestMapping(path="/dao/find-staff/shop-id",method=RequestMethod.GET)
	public List<Staffinfo> findStaffByShopId(@RequestParam int shopId);
}


