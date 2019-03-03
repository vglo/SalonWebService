/**
 * 
 */
package com.habbib.sms.request.model;

/**
 * @author yash
 *
 */
public class EmailParam {
	
	private String toEmailId;
	
	private int custId;
	
	private int shopId;
	
	private int billId;

	/**
	 * @return the toEmailId
	 */
	public String getToEmailId() {
		return toEmailId;
	}

	/**
	 * @param toEmailId the toEmailId to set
	 */
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
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
	
	

}
