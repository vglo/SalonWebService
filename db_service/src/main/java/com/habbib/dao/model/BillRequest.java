package com.habbib.dao.model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bill database table.
 * 
 */

public class BillRequest  {


	private String billNo;

	private double cgstPer;

	private double csgtVal;

	private Date date;

	private double discountPer;

	private double discountVal;

	private double grandTotal;

	private double sgstPer;

	private double sgstVal;

	private String time;

	private double total;

	private int custId;
	
	private int shopId;
	
	private List<BillhasserviceRequest> billhasservices;

	private int paymentType;

	public BillRequest() {
	}

	

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public double getCgstPer() {
		return this.cgstPer;
	}

	public void setCgstPer(double cgstPer) {
		this.cgstPer = cgstPer;
	}

	public double getCsgtVal() {
		return this.csgtVal;
	}

	public void setCsgtVal(double csgtVal) {
		this.csgtVal = csgtVal;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDiscountPer() {
		return this.discountPer;
	}

	public void setDiscountPer(double discountPer) {
		this.discountPer = discountPer;
	}

	public double getDiscountVal() {
		return this.discountVal;
	}

	public void setDiscountVal(double discountVal) {
		this.discountVal = discountVal;
	}

	public double getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double getSgstPer() {
		return this.sgstPer;
	}

	public void setSgstPer(double sgstPer) {
		this.sgstPer = sgstPer;
	}

	public double getSgstVal() {
		return this.sgstVal;
	}

	public void setSgstVal(double sgstVal) {
		this.sgstVal = sgstVal;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	

	public List<BillhasserviceRequest> getBillhasservices() {
		return this.billhasservices;
	}

	public void setBillhasservices(List<BillhasserviceRequest> billhasservices) {
		this.billhasservices = billhasservices;
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
	 * @return the paymentType
	 */
	public int getPaymentType() {
		return paymentType;
	}



	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}


	
	
}