/**
 * 
 */
package com.habbib.service.feign.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.service.dbfeign.client.DBServiceFeignClient;
import com.habbib.service.request.model.SalonserviceRequest;
import com.habbib.service.response.model.Salonservice;
import com.habbib.service.response.model.Shopinfo;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {


	@Override
	public Optional<Shopinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salonservice> getSalonServices(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Salonservice> getServiceInfo(int serviceId,int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salonservice saveService(SalonserviceRequest salonServiceReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salonservice validateService(String name, double price, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salonservice updateService(SalonserviceRequest salonServiceReq, int serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteService(int serviceId, int shopId) {
		// TODO Auto-generated method stub
		
	}

}
