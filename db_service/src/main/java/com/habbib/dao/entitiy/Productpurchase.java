package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the productpurchase database table.
 * 
 */
@Entity(name="ProductPurchase")
public class Productpurchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductPurchase;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double gst;

	private double price;

	private int quantity;

	//bi-directional many-to-one association to Productinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="productId")
	private Productinfo productinfo;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	public Productpurchase() {
	}

	public int getIdProductPurchase() {
		return this.idProductPurchase;
	}

	public void setIdProductPurchase(int idProductPurchase) {
		this.idProductPurchase = idProductPurchase;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getGst() {
		return this.gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Productinfo getProductinfo() {
		return this.productinfo;
	}

	public void setProductinfo(Productinfo productinfo) {
		this.productinfo = productinfo;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}