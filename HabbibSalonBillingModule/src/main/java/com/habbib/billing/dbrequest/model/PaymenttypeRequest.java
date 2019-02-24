package com.habbib.billing.dbrequest.model;

import java.util.List;


/**
 * The persistent class for the paymenttype database table.
 * 
 */
public class PaymenttypeRequest  {

	private int idPaymentType;

	private String integrationKey;

	private int shopId;

	private String type;


	public PaymenttypeRequest() {
	}

	public int getIdPaymentType() {
		return this.idPaymentType;
	}

	public void setIdPaymentType(int idPaymentType) {
		this.idPaymentType = idPaymentType;
	}

	public String getIntegrationKey() {
		return this.integrationKey;
	}

	public void setIntegrationKey(String integrationKey) {
		this.integrationKey = integrationKey;
	}

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	

}