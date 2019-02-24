package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the billhasservice database table.
 * 
 */
@Entity(name="BillHasService")
public class Billhasservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBillHasService;

	private int quantity;

	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idBill")
	private Bill bill;

	//bi-directional many-to-one association to Salonservice
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idSalonService")
	private Salonservice salonservice;

	public Billhasservice() {
	}

	public int getIdBillHasService() {
		return this.idBillHasService;
	}

	public void setIdBillHasService(int idBillHasService) {
		this.idBillHasService = idBillHasService;
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

	public Salonservice getSalonservice() {
		return this.salonservice;
	}

	public void setSalonservice(Salonservice salonservice) {
		this.salonservice = salonservice;
	}

}