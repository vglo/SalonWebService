package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.SalonServiceRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.entitiy.Salonservice;
import com.habbib.dao.entitiy.Shopinfo;

@RestController
@RequestMapping("/dao")
public class SalonServiceController {

	@Autowired
	private SalonServiceRepository salonService;
	
	@Autowired
	private ShopInfoRepository shopInfo;
	
	@RequestMapping(path="/salon-service/{shopId}",method=RequestMethod.GET)
	public List<Salonservice> getSalonServices(@PathVariable int shopId) {
		 Optional<Shopinfo> shopDetails = shopInfo.findById(shopId);
		 List<Salonservice> services = salonService.findByShopinfo(shopDetails.get());
		 return services;
	}
	
	@RequestMapping(path="/get-service-info/{serviceId}",method=RequestMethod.GET)
	public Optional<Salonservice> getServiceInfo(@PathVariable int serviceId){
		Optional<Salonservice> serviceInfo = salonService.findById(serviceId);
		return serviceInfo;
	}
	
}
