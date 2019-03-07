/**
 * 
 */
package com.habbib.billing.request.model;

/**
 * @author yash
 *
 */
public class BillhasserviceClientReq {

	private int serviceId;
	
	private int quant;
	
	private int staffId;

	/**
	 * @return the serviceId
	 */
	public int getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the quant
	 */
	public int getQuant() {
		return quant;
	}

	/**
	 * @param quant the quant to set
	 */
	public void setQuant(int quant) {
		this.quant = quant;
	}

	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	
}
