/**
 * 
 */
package com.habbib.billing.request.model;

import java.util.List;

/**
 * @author yash
 *
 */
public class BillRequest {

	
	private double cgstPer;
	
	private double sgstPer;
	
	private double descountPer;
	
	private double discountVal;

	private List<BillHasService> billHasService;
	
	private int customerId;
	
	private int serviceStaffId;
	
	private int shopId;

	
	

	/**
	 * @return the billHasService
	 */
	public List<BillHasService> getBillHasService() {
		return billHasService;
	}

	/**
	 * @param billHasService the billHasService to set
	 */
	public void setBillHasService(List<BillHasService> billHasService) {
		this.billHasService = billHasService;
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
	 * @return the serviceStaffId
	 */
	public int getServiceStaffId() {
		return serviceStaffId;
	}

	/**
	 * @param serviceStaffId the serviceStaffId to set
	 */
	public void setServiceStaffId(int serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
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
	 * @return the cgstPer
	 */
	public double getCgstPer() {
		return cgstPer;
	}

	/**
	 * @param cgstPer the cgstPer to set
	 */
	public void setCgstPer(double cgstPer) {
		this.cgstPer = cgstPer;
	}

	/**
	 * @return the sgstPer
	 */
	public double getSgstPer() {
		return sgstPer;
	}

	/**
	 * @param sgstPer the sgstPer to set
	 */
	public void setSgstPer(double sgstPer) {
		this.sgstPer = sgstPer;
	}

	/**
	 * @return the descountPer
	 */
	public double getDescountPer() {
		return descountPer;
	}

	/**
	 * @param descountPer the descountPer to set
	 */
	public void setDescountPer(double descountPer) {
		this.descountPer = descountPer;
	}

	/**
	 * @return the discountVal
	 */
	public double getDiscountVal() {
		return discountVal;
	}

	/**
	 * @param discountVal the discountVal to set
	 */
	public void setDiscountVal(double discountVal) {
		this.discountVal = discountVal;
	}
	
	
	
	
	
}
