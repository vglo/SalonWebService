package com.habbib.shop.feign.client.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.shop.feign.client.DBServiceFeignClient;
import com.habbib.shop.request.model.ShopinfoRequest;
import com.habbib.shop.response.model.Shopinfo;

@Component
public class DBServiceFallback implements DBServiceFeignClient{

	@Override
	public Shopinfo saveShopDetails(ShopinfoRequest shopDetails) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteShop(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Shopinfo> findAllShops() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Shopinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uddateShop(Shopinfo shop) {
		// TODO Auto-generated method stub
		
	}

	
}
