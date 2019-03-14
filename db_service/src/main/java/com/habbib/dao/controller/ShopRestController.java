package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.JPArepository.ShopTypeRepository;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.entitiy.Shoptype;
import com.habbib.dao.model.ShopinfoRequest;
import com.habbib.dao.service.DBService;

@RestController
@RequestMapping(value="/dao")
public class ShopRestController {

	@Autowired
	private ShopInfoRepository shopInfo;
	
	@Autowired
	private ShopTypeRepository shopTypeRepo;
	
	@Autowired
	private DBService dbService;
	@RequestMapping(path="/save-shop-detail",method=RequestMethod.POST)
	public Shopinfo saveShopDetails(@RequestBody ShopinfoRequest shop) {
		
		Shopinfo shopDetails = dbService.convertModelToEntityShop(shop);
		Shopinfo shopInfo1 = shopInfo.save(shopDetails);
		return shopInfo1;
	}
	
	@RequestMapping(path="/fetch-shop/parent-id",method=RequestMethod.GET)
	public List<Shopinfo> fetchShopByParentId(@RequestParam int parentId){
		
		Shopinfo parentShop = shopInfo.getOne(parentId);
		List<Shopinfo> shopsList = shopInfo.findByShopinfo(parentShop);
		if(shopsList == null)
			throw new NullPointerException();
		return shopsList;
	}
	
	@RequestMapping(path="/delete-shop/{id}",method=RequestMethod.DELETE)
	public void deleteShop(@PathVariable("id") int id) {
		shopInfo.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-shops",method=RequestMethod.GET)
	public List<Shopinfo> findAllShops() {
		List<Shopinfo> shopsList = shopInfo.findAll();
		return shopsList;
	}
	
	@RequestMapping(path="/fetch/shopById/{shopId}",method=RequestMethod.GET)
	public Optional<Shopinfo> findByShopId(@PathVariable() int shopId) {
		Optional<Shopinfo> shop =  shopInfo.findById(shopId);
		return shop;
	}
	
	@RequestMapping(path="/update-shop",method=RequestMethod.PUT)
	public void uddateShop(@RequestBody Shopinfo shop) {
		shopInfo.saveAndFlush(shop);
	}
	
	
	@RequestMapping(path="/find-shop-type",method=RequestMethod.GET)
	public List<Shoptype> findAllShopType(){
		List<Shoptype> shopTypes = shopTypeRepo.findAll();
		if(shopTypes == null)
			throw new NullPointerException();
		return shopTypes;
	}
	
	@RequestMapping(path="/fetch-shops/shop-type",method=RequestMethod.GET)
	public List<Shopinfo> fetchShopByShopType(@RequestParam int shopTypeId){
		Shoptype shopType = shopTypeRepo.getOne(shopTypeId);
		List<Shopinfo> shopList = shopInfo.findByShoptype(shopType);
		if(shopList == null)
			return null;
		return shopList;
	}
}
