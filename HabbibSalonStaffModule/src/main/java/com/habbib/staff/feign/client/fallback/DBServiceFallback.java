/**
 * 
 */
package com.habbib.staff.feign.client.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.staff.feign.clients.DBServiceFeignClient;
import com.habbib.staff.request.model.StaffinfoRequest;
import com.habbib.staff.response.model.Staffinfo;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {

	

	@Override
	public Staffinfo saveStaffInfo(StaffinfoRequest staffInfoReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staffinfo> findStaffByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Staffinfo> validateStaff(String mobileNum, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staffinfo findStaffByid(int staffId) {
		// TODO Auto-generated method stub
		return null;
	}

}
