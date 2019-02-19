package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the salonservice database table.
 * 
 */
@Entity
@NamedQuery(name="Salonservice.findAll", query="SELECT s FROM Salonservice s")
public class Salonservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSalonService;

	private String name;

	private double price;

	//bi-directional many-to-one association to Billhasservice
	@JsonIgnore
	@OneToMany(mappedBy="salonservice")
	private List<Billhasservice> billhasservices;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	public Salonservice() {
	}

	public int getIdSalonService() {
		return this.idSalonService;
	}

	public void setIdSalonService(int idSalonService) {
		this.idSalonService = idSalonService;
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

	public List<Billhasservice> getBillhasservices() {
		return this.billhasservices;
	}

	public void setBillhasservices(List<Billhasservice> billhasservices) {
		this.billhasservices = billhasservices;
	}

	public Billhasservice addBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().add(billhasservice);
		billhasservice.setSalonservice(this);

		return billhasservice;
	}

	public Billhasservice removeBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().remove(billhasservice);
		billhasservice.setSalonservice(null);

		return billhasservice;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}