package com.habbib.dao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.ProductInfoRepository;
import com.habbib.dao.entitiy.Productinfo;

@RestController
@RequestMapping("dao")
public class ProductInfoController {
	
	@Autowired
	private ProductInfoRepository productInfoRepo;

	@RequestMapping(path="/save-product",method=RequestMethod.POST)
	public Productinfo saveProductInfo() {
		return null;
		
	}
	
	@RequestMapping(path="/find-product/shop-id",method=RequestMethod.GET)
	public List<Productinfo> findProductsByShopId(@RequestParam int shopId) {
		return null;
		
	}
	
	@RequestMapping(path="/find-product/prod-id",method=RequestMethod.GET)
	public Productinfo findProductByProdId(@RequestParam int prodId,@RequestParam int shopId) {
		return null;
	}
}
