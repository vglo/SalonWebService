/**
 * 
 */
package com.habbib.billing.response.model;

import java.util.Date;

/**
 * @author yash
 *
 */
public class BillResponse {
	
	private int idBill;
	
	private String billNo;
	
	private double total;
	
	private double discountPer;

	private double discountVal;

	private double grandTotal;

	private double sgstPer;

	private double sgstVal;

	private double cgstPer;

	private double csgtVal;
	
	private String time;
	
	private Date date;

	
	/**
	 * @return the idBill
	 */
	public int getIdBill() {
		return idBill;
	}


	/**
	 * @param idBill the idBill to set
	 */
	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}


	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}


	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}


	/**
	 * @param sgstPer the sgstPer to set
	 */
	public void setSgstPer(int sgstPer) {
		this.sgstPer = sgstPer;
	}

	
	/**
	 * @param sgstVal the sgstVal to set
	 */
	public void setSgstVal(int sgstVal) {
		this.sgstVal = sgstVal;
	}

	
	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	

	/**
	 * @param discountPer the discountPer to set
	 */
	public void setDiscountPer(int discountPer) {
		this.discountPer = discountPer;
	}

	/**
	 * @param discountVal the discountVal to set
	 */
	public void setDiscountVal(int discountVal) {
		this.discountVal = discountVal;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the discountPer
	 */
	public double getDiscountPer() {
		return discountPer;
	}

	/**
	 * @param discountPer the discountPer to set
	 */
	public void setDiscountPer(double discountPer) {
		this.discountPer = discountPer;
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

	/**
	 * @return the grandTotal
	 */
	public double getGrandTotal() {
		return grandTotal;
	}

	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
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
	 * @return the sgstVal
	 */
	public double getSgstVal() {
		return sgstVal;
	}

	/**
	 * @param sgstVal the sgstVal to set
	 */
	public void setSgstVal(double sgstVal) {
		this.sgstVal = sgstVal;
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
	 * @return the csgtVal
	 */
	public double getCsgtVal() {
		return csgtVal;
	}

	/**
	 * @param csgtVal the csgtVal to set
	 */
	public void setCsgtVal(double csgtVal) {
		this.csgtVal = csgtVal;
	}
	
	
	
}
