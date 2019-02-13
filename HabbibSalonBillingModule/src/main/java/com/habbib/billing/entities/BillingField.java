package com.habbib.billing.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BillingField {
	
	@Id
	private int billNumber;
	
	private String categories;
	
	private String styliesName;
	
	private long amount;

	@OneToOne
	private CustomerInfo customerDetail;
	
	
	
	public CustomerInfo getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerInfo customerDetail) {
		this.customerDetail = customerDetail;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getStyliesName() {
		return styliesName;
	}

	public void setStyliesName(String styliesName) {
		this.styliesName = styliesName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	

	
}
