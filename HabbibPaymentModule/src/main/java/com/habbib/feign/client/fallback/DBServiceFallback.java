/**
 * 
 */
package com.habbib.feign.client.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.habbib.feign.client.DBServiceFeignClient;
import com.habbib.request.model.PaymentTypeRequest;
import com.habbib.response.model.Paymenttype;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {

	@Override
	public List<Paymenttype> fetchAllPaymentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paymenttype fetchByPaymentTypeID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paymenttype fetchByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paymenttype savePaymentType(PaymentTypeRequest paymentType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paymenttype> fetchPaymentTypeByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}