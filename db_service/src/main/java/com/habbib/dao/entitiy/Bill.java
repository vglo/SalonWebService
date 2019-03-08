package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBill;

	private String billNo;

	private String billType;

	private double cgstPer;

	private double csgtVal;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double discountPer;

	private double discountVal;

	private double grandTotal;

	private double sgstPer;

	private double sgstVal;

	private String time;

	private double total;

	//bi-directional many-to-one association to Customerinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="custId")
	private Customerinfo customerinfo;

	//bi-directional many-to-one association to Paymenttype
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="paymentType")
	private Paymenttype paymenttype;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	//bi-directional many-to-one association to Billhasservice
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy="bill",cascade=CascadeType.PERSIST)
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

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
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

	public Paymenttype getPaymenttype() {
		return this.paymenttype;
	}

	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public List<Billhasservice> getBillhasservices() {
		return this.billhasservices;
	}

	public void setBillhasservices(List<Billhasservice> billhasservices) {
		this.billhasservices = billhasservices;
	}

	public Billhasservice addBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().add(billhasservice);
		billhasservice.setBill(this);

		return billhasservice;
	}

	public Billhasservice removeBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().remove(billhasservice);
		billhasservice.setBill(null);

		return billhasservice;
	}

}