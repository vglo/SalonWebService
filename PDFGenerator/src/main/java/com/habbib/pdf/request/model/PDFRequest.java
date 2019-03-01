package com.habbib.pdf.request.model;

public class PDFRequest {
	
	private int billId;
	
	private int customerId;
	
	private int shopId;

	/**
	 * @return the billId
	 */
	public int getBillId() {
		return billId;
	}

	/**
	 * @param billId the billId to set
	 */
	public void setBillId(int billId) {
		this.billId = billId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	
	

}
