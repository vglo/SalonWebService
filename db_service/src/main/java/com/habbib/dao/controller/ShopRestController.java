package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.entitiy.Shopinfo;

@RestController
@RequestMapping(value="/dao")
public class ShopRestController {

	@Autowired
	private ShopInfoRepository shopInfo;
	
	@RequestMapping(path="/save-shop-detail")
	public void saveShopDetails(@RequestBody Shopinfo shopDetails) {
		shopInfo.save(shopDetails);
	}
	
	@RequestMapping(path="/delete-shop/{id}")
	public void deleteShop(@PathVariable("id") int id) {
		shopInfo.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-shops")
	public List<Shopinfo> findAllShops() {
		List<Shopinfo> shopsList = shopInfo.findAll();
		return shopsList;
	}
	
	@RequestMapping(path="/fetch/shopById/{shopId}")
	public Optional<Shopinfo> findByShopId(@PathVariable() int shopId) {
		Optional<Shopinfo> shop =  shopInfo.findById(shopId);
		return shop;
	}
	
	@RequestMapping(path="/update-shop")
	public void uddateShop(@RequestBody Shopinfo shop) {
		shopInfo.saveAndFlush(shop);
	}
	
}
