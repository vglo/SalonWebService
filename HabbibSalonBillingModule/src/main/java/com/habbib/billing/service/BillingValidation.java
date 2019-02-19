package com.habbib.billing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.habbib.billing.model.Bill;

@Service
public class BillingValidation {

	private static final Logger LOG = LoggerFactory.getLogger(BillingValidation.class);
	
	/*
	 * To validate bill fields
	 * 
	 */
	public void validateBill(Bill bill) {
		LOG.info("Inside Billing Validation Method");
		
		
	}
}
