package com.habbib.billing.service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.habbib.billing.model.Salonservice;
import com.habbib.billing.serviceIntefaces.IBilling;

@Service
public class Billing implements IBilling {

	/* (non-Javadoc)
	 * @see com.habbib.billing.serviceIntefaces.IBilling#calculateBill()
	 */
	@Override
	public double calculateBill(Map<Optional<Salonservice>, Integer> serviceMap) {
		double totalservicePay = 0;
		Set<Optional<Salonservice>> salonObj = serviceMap.keySet();
		for(Optional<Salonservice> salonInfo : salonObj) {
			double quantity = serviceMap.get(salonInfo);
			//Salonservice service = salonInfo.get(); 
			totalservicePay  +=  quantity*salonInfo.get().getPrice();
		}
		return totalservicePay;
	}

	
}
