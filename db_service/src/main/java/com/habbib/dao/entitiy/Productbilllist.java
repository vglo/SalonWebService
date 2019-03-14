package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the productbilllist database table.
 * 
 */
@Entity(name="ProductBillList")
public class Productbilllist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductBillList;

	private double amount;

	private double discount;

	private double mrp;

	private int quantity;

	//bi-directional many-to-one association to Productbill
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="productBill")
	private Productbill productbill;

	//bi-directional many-to-one association to Productinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="productId")
	private Productinfo productinfo;

	public Productbilllist() {
	}

	public int getIdProductBillList() {
		return this.idProductBillList;
	}

	public void setIdProductBillList(int idProductBillList) {
		this.idProductBillList = idProductBillList;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getMrp() {
		return this.mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Productbill getProductbill() {
		return this.productbill;
	}

	public void setProductbill(Productbill productbill) {
		this.productbill = productbill;
	}

	public Productinfo getProductinfo() {
		return this.productinfo;
	}

	public void setProductinfo(Productinfo productinfo) {
		this.productinfo = productinfo;
	}

}