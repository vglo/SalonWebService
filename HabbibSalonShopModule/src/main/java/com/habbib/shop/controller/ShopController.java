package com.habbib.shop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.habbib.shop.response.model.Shoptype;
import com.habib.utility.DefaultMessage;

import feign.RetryableException;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private DBServiceFeignClient dbService;
	
	@RequestMapping(path="/save-shop-details",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Shopinfo>> saveFranchiesDetails(@Valid @ModelAttribute ShopinfoRequest shopInfo){
		DefaultMessage<Shopinfo> defaultResponse = new DefaultMessage<Shopinfo>();
		
		if(shopInfo == null)
			throw new NullPointerException();
		
		try {
		
		Shopinfo shop = dbService.saveShopDetails(shopInfo);
		if(shop != null) {
			defaultResponse.setResponse(shop);
			defaultResponse.setResponseCode("201");
			defaultResponse.setResponseMessage("Shop details saved successfully");
			return new ResponseEntity<DefaultMessage<Shopinfo>>(defaultResponse,HttpStatus.CREATED);
		}else {
			defaultResponse.setResponse(shop);
			defaultResponse.setResponseCode("400");
			defaultResponse.setResponseMessage("Shop details not saved");
			return new ResponseEntity<DefaultMessage<Shopinfo>>(defaultResponse,HttpStatus.BAD_REQUEST);
		}
		}catch(RetryableException exception) {
			exception.getCause();
			exception.getLocalizedMessage();
			
		}
		return null;
	}
	
	/*
	 * @RequestMapping(path="/find-all", method=RequestMethod.GET) public
	 * List<Shopinfo> findAllShopDetails() { List<Shopinfo> shopList =
	 * dbService.findAllShops(); return shopList; }
	 */
	
	
	@RequestMapping(path="/find-by-id/{shopId}",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Shopinfo>> findByShopId(@PathVariable int shopId){
		DefaultMessage<Shopinfo> defaultResponse = new DefaultMessage<Shopinfo>();
		Optional<Shopinfo> shop = dbService.findByShopId(shopId);
		if(shop.isPresent()) {
			defaultResponse.setResponse(shop.get());
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Shop details find");
			return new ResponseEntity<DefaultMessage<Shopinfo>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(null);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Shop details not saved");
			return new ResponseEntity<DefaultMessage<Shopinfo>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/delete-shop",method=RequestMethod.DELETE)
	public BodyBuilder deleteShopDetail(@RequestParam int shopId) {
		if(shopId == 0)
			ResponseEntity.badRequest();
		dbService.deleteShop(shopId);
		return ResponseEntity.ok();
	}
	
	@RequestMapping(path="/find-shop-type",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Shoptype>>> fetchAllShopType(){
		DefaultMessage<List<Shoptype>> defaultResponse = new DefaultMessage<List<Shoptype>>();
		List<Shoptype> shopTypes = dbService.findAllShopType();
		if(shopTypes.size() != 0 && shopTypes != null) {
			defaultResponse.setResponse(shopTypes);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Please find the list of all shop types");
			return new ResponseEntity<DefaultMessage<List<Shoptype>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(null);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("Currently there are no shop types present, list is empty");
			return new ResponseEntity<DefaultMessage<List<Shoptype>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/find-shop/parent-shop",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Shopinfo>>> findShopByParentShop(@RequestParam int parentShopId){
		DefaultMessage<List<Shopinfo>> defaultResponse = new DefaultMessage<List<Shopinfo>>();
		List<Shopinfo> shopList = dbService.fetchShopByParentId(parentShopId);
		if(shopList.size() != 0 && shopList != null) {
			defaultResponse.setResponse(shopList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Shop details ");
			return new ResponseEntity<DefaultMessage<List<Shopinfo>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(null);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("Shop details not found");
			return new ResponseEntity<DefaultMessage<List<Shopinfo>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(path="/find-shop/shop-type",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Shopinfo>>> findshopByTypeId(@RequestParam(required=true) int shopTypeId){
		DefaultMessage<List<Shopinfo>> defaultResponse = new DefaultMessage<List<Shopinfo>>();
		List<Shopinfo> shopList = dbService.fetchShopByShopType(shopTypeId);
		if(shopList.size() != 0 && shopList != null) {
			defaultResponse.setResponse(shopList);
			defaultResponse.setResponseCode("200");
			defaultResponse.setResponseMessage("Shop details ");
			return new ResponseEntity<DefaultMessage<List<Shopinfo>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(null);
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("Shop details not found");
			return new ResponseEntity<DefaultMessage<List<Shopinfo>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
}
