/**
 * 
 */
package com.habbib.billing.request.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yash
 *
 */
public class BillClientRequest {

	
	private double cgstPer;
	
	private double sgstPer;
	
	private double descountPer;
	
	private double discountVal;

	@NotEmpty
	private List<BillHasService> billHasService;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private int serviceStaffId;
	
	@NotNull
	private int shopId;
	
	@NotNull
	private int type;
	
	
	
	

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	


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