package com.habbib.shop.feign.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.shop.feign.client.fallback.DBServiceFallback;
import com.habbib.shop.request.model.ShopinfoRequest;
import com.habbib.shop.response.model.Shopinfo;
import com.habbib.shop.response.model.Shoptype;



@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {
	
	@RequestMapping(path="/dao/save-shop-detail",method=RequestMethod.POST)
	public Shopinfo saveShopDetails(@RequestBody ShopinfoRequest shopDetails);
	
	@RequestMapping(path="/dao/fetch-shop/parent-id",method=RequestMethod.GET)
	public List<Shopinfo> fetchShopByParentId(@RequestParam int parentId);
	
	@RequestMapping(path="/dao/find-shop-type",method=RequestMethod.GET)
	public List<Shoptype> findAllShopType();
	
	@RequestMapping(path="/dao/delete-shop/{id}",method=RequestMethod.DELETE)
	public void deleteShop(@PathVariable("id") int id);
	
	@RequestMapping(path="/dao/fetch/all-shops",method=RequestMethod.GET)
	public List<Shopinfo> findAllShops();
	
	@RequestMapping(path="/dao/fetch/shopById/{shopId}",method=RequestMethod.GET)
	public Optional<Shopinfo> findByShopId(@PathVariable() int shopId);
	
	@RequestMapping(path="/dao/update-shop",method=RequestMethod.PUT)
	public void uddateShop(@RequestBody Shopinfo shop);
	
}
