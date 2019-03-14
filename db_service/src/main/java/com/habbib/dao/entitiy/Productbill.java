package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the productbill database table.
 * 
 */
@Entity(name="ProductBill")
public class Productbill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductBill;

	private String billNo;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double grandTotal;

	private String time;

	private double total;

	private double totalDiscount;

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

	//bi-directional many-to-one association to Productbilllist
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy="productbill")
	private List<Productbilllist> productbilllists;

	public Productbill() {
	}

	public int getIdProductBill() {
		return this.idProductBill;
	}

	public void setIdProductBill(int idProductBill) {
		this.idProductBill = idProductBill;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
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

	public double getTotalDiscount() {
		return this.totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
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

	public List<Productbilllist> getProductbilllists() {
		return this.productbilllists;
	}

	public void setProductbilllists(List<Productbilllist> productbilllists) {
		this.productbilllists = productbilllists;
	}

	public Productbilllist addProductbilllist(Productbilllist productbilllist) {
		getProductbilllists().add(productbilllist);
		productbilllist.setProductbill(this);

		return productbilllist;
	}

	public Productbilllist removeProductbilllist(Productbilllist productbilllist) {
		getProductbilllists().remove(productbilllist);
		productbilllist.setProductbill(null);

		return productbilllist;
	}

}