/**
 * 
 */
package com.habbib.sms.feign.client;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.sms.response.model.Bill;
import com.habbib.sms.response.model.Customerinfo;

/**
 * @author yash
 *
 */
@Component
public class DBFeignClientFallback implements DBServiceFeignClient{

	@Override
	public Optional<Bill> findByBillId(int billId, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customerinfo> findByCustId(int customerId, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

}
