package com.habbib.sms.response.model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bill database table.
 * 
 */
public class Bill  {

	private int idBill;

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

	private Customerinfo customerinfo;

	private Shopinfo shopinfo;

	private Paymenttype paymenttype;
	
	private Staffinfo staffinfo;
	
	private List<Billhasservice> billhasservices;

	public Bill() {
	}

	public int getIdBill() {
		return this.idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
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

	public Customerinfo getCustomerinfo() {
		return this.customerinfo;
	}

	public void setCustomerinfo(Customerinfo customerinfo) {
		this.customerinfo = customerinfo;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	
	public Paymenttype getPaymenttype() {
		return this.paymenttype;
	}

	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}

	/**
	 * @return the staffinfo
	 */
	public Staffinfo getStaffinfo() {
		return staffinfo;
	}

	/**
	 * @param staffinfo the staffinfo to set
	 */
	public void setStaffinfo(Staffinfo staffinfo) {
		this.staffinfo = staffinfo;
	}

	/**
	 * @return the billhasservices
	 */
	public List<Billhasservice> getBillhasservices() {
		return billhasservices;
	}

	/**
	 * @param billhasservices the billhasservices to set
	 */
	public void setBillhasservices(List<Billhasservice> billhasservices) {
		this.billhasservices = billhasservices;
	}

	
	
}