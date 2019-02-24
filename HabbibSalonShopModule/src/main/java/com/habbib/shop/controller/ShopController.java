package com.habbib.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.shop.feign.client.DBServiceFeignClient;
import com.habbib.shop.request.model.ShopinfoRequest;
import com.habbib.shop.response.model.Shopinfo;
import com.habbib.utility.DefaultMessage;

import feign.RetryableException;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private DBServiceFeignClient dbService;
	
	@RequestMapping(path="/save-shop-details",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Shopinfo>> saveShopDetail(@ModelAttribute ShopinfoRequest shopInfo){
		DefaultMessage<Shopinfo> defaultResponse = new DefaultMessage<Shopinfo>();
		try {
		
		Shopinfo shop = dbService.saveShopDetails(shopInfo);
		defaultResponse.setResponse(shop);
		defaultResponse.setResponseCode("200");
		ResponseEntity<DefaultMessage<Shopinfo>> response = ResponseEntity.ok(defaultResponse);
		return response;
		}catch(RetryableException exception) {
			exception.getCause();
			exception.getLocalizedMessage();
			
		}
		return null;
	}
	
	@RequestMapping(path="/find-all", method=RequestMethod.GET)
	public List<Shopinfo> findAllShopDetails() {
		List<Shopinfo> shopList = dbService.findAllShops();
		return shopList;
	}

	@RequestMapping(path="/find-by-id/{shopId}",method=RequestMethod.GET)
	public Shopinfo findByShopId(@PathVariable int shopId){
		Optional<Shopinfo> shop = dbService.findByShopId(shopId);
		return shop.get();
	}
	
	@RequestMapping(path="/delete-shop",method=RequestMethod.DELETE)
	public BodyBuilder deleteShopDetail(@RequestParam int shopId) {
		if(shopId == 0)
			ResponseEntity.badRequest();
		dbService.deleteShop(shopId);
		return ResponseEntity.ok();
	}
	
	
}
