package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the productinfo database table.
 * 
 */
@Entity(name="ProductInfo")
public class Productinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductInfo;

	private String brand;

	private String hsn;

	private String name;

	private String sku;

	//bi-directional many-to-one association to Productbilllist
	@JsonIgnore
	@OneToMany(mappedBy="productinfo")
	private List<Productbilllist> productbilllists;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	//bi-directional many-to-one association to Productpurchase
	@JsonIgnore
	@OneToMany(mappedBy="productinfo")
	private List<Productpurchase> productpurchases;

	//bi-directional many-to-one association to Productstock
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy="productinfo")
	private List<Productstock> productstocks;

	public Productinfo() {
	}

	public int getIdProductInfo() {
		return this.idProductInfo;
	}

	public void setIdProductInfo(int idProductInfo) {
		this.idProductInfo = idProductInfo;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getHsn() {
		return this.hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public List<Productbilllist> getProductbilllists() {
		return this.productbilllists;
	}

	public void setProductbilllists(List<Productbilllist> productbilllists) {
		this.productbilllists = productbilllists;
	}

	public Productbilllist addProductbilllist(Productbilllist productbilllist) {
		getProductbilllists().add(productbilllist);
		productbilllist.setProductinfo(this);

		return productbilllist;
	}

	public Productbilllist removeProductbilllist(Productbilllist productbilllist) {
		getProductbilllists().remove(productbilllist);
		productbilllist.setProductinfo(null);

		return productbilllist;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public List<Productpurchase> getProductpurchases() {
		return this.productpurchases;
	}

	public void setProductpurchases(List<Productpurchase> productpurchases) {
		this.productpurchases = productpurchases;
	}

	public Productpurchase addProductpurchas(Productpurchase productpurchas) {
		getProductpurchases().add(productpurchas);
		productpurchas.setProductinfo(this);

		return productpurchas;
	}

	public Productpurchase removeProductpurchas(Productpurchase productpurchas) {
		getProductpurchases().remove(productpurchas);
		productpurchas.setProductinfo(null);

		return productpurchas;
	}

	public List<Productstock> getProductstocks() {
		return this.productstocks;
	}

	public void setProductstocks(List<Productstock> productstocks) {
		this.productstocks = productstocks;
	}

	public Productstock addProductstock(Productstock productstock) {
		getProductstocks().add(productstock);
		productstock.setProductinfo(this);

		return productstock;
	}

	public Productstock removeProductstock(Productstock productstock) {
		getProductstocks().remove(productstock);
		productstock.setProductinfo(null);

		return productstock;
	}

}