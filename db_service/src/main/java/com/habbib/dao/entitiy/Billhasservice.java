package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the billhasservice database table.
 * 
 */
@Entity
@NamedQuery(name="Billhasservice.findAll", query="SELECT b FROM Billhasservice b")
public class Billhasservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idBillHasService;

	private double amount;

	private String name;

	private double price;

	private int quantity;

	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idBill")
	private Bill bill;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	//bi-directional many-to-one association to Staffinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="staffInfo")
	private Staffinfo staffinfo;

	public Billhasservice() {
	}

	

	/**
	 * @return the idBillHasService
	 */
	public long getIdBillHasService() {
		return idBillHasService;
	}



	/**
	 * @param idBillHasService the idBillHasService to set
	 */
	public void setIdBillHasService(long idBillHasService) {
		this.idBillHasService = idBillHasService;
	}



	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public Staffinfo getStaffinfo() {
		return this.staffinfo;
	}

	public void setStaffinfo(Staffinfo staffinfo) {
		this.staffinfo = staffinfo;
	}

}