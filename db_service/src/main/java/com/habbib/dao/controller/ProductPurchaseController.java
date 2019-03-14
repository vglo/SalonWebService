package com.habbib.dao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.entitiy.Productpurchase;

@RestController
@RequestMapping("dao")
public class ProductPurchaseController {

	@RequestMapping(path="/save-product-purchase",method=RequestMethod.POST)
	public Productpurchase saveProductPurchase() {
		return null;
		
	}
	
	@RequestMapping(path="/find-prod-purchase/id",method=RequestMethod.GET)
	public Productpurchase findPurchaseById(@RequestParam int purchaseId,@RequestParam int shopId) {
		return null;
		
	}
	
	@RequestMapping(path="/find-purchase/prod-id/shop-id",method=RequestMethod.GET)
	public Productpurchase findProdPurchaseByProdIdAndShop(@RequestParam int prodId,@RequestParam int shopId) {
		return null;
		
	}
	
	
}
