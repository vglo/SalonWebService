package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.SalonServiceRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.entitiy.Salonservice;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.model.SalonserviceRequest;
import com.habbib.dao.service.DBService;

@RestController
@RequestMapping("/dao")
public class SalonServiceController {

	@Autowired
	private SalonServiceRepository salonService;
	
	@Autowired
	private ShopInfoRepository shopInfo;
	
	@Autowired
	private DBService dbService;
	
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
	
	
	@RequestMapping(path="/save-service",method=RequestMethod.POST)
	public Salonservice saveService(@RequestBody SalonserviceRequest salonServiceReq) {
		Salonservice serviceEntity = dbService.convertServiceModelToEntity(salonServiceReq);
		Salonservice service = salonService.save(serviceEntity);
		return service;
	}
	
	@RequestMapping(path="/validate-service",method=RequestMethod.GET)
	public Salonservice validateService(@RequestParam String name,@RequestParam double price,@RequestParam int shopId) {
		Shopinfo shop = shopInfo.getOne(shopId);
		Optional<Salonservice> service = salonService.findByNameAndPriceAndShopinfo(name, price, shop);
		if(service.isPresent())
			return service.get();
		return null;
	}
	
}
