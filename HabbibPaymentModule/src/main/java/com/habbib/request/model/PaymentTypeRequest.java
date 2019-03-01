package com.habbib.request.model;

public class PaymentTypeRequest {

	private String integrationKey;

	private int shopId;

	private String type;

	/**
	 * @return the integrationKey
	 */
	public String getIntegrationKey() {
		return integrationKey;
	}

	/**
	 * @param integrationKey the integrationKey to set
	 */
	public void setIntegrationKey(String integrationKey) {
		this.integrationKey = integrationKey;
	}

	/**
	 * @return the shopId
	 */
	public int getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
