package com.habbib.dao.model;

import java.util.Date;


/**
 * The persistent class for the appointment database table.
 * 
 */

public class AppointmentRequest  {

	
	private int idAppointment;

	private Date date;

	private String time;

	private int idCustomerInfo;

	private int idShopInfo;

	public AppointmentRequest() {
	}

	public int getIdAppointment() {
		return this.idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the idCustomerInfo
	 */
	public int getIdCustomerInfo() {
		return idCustomerInfo;
	}

	/**
	 * @param idCustomerInfo the idCustomerInfo to set
	 */
	public void setIdCustomerInfo(int idCustomerInfo) {
		this.idCustomerInfo = idCustomerInfo;
	}

	/**
	 * @return the idShopInfo
	 */
	public int getIdShopInfo() {
		return idShopInfo;
	}

	/**
	 * @param idShopInfo the idShopInfo to set
	 */
	public void setIdShopInfo(int idShopInfo) {
		this.idShopInfo = idShopInfo;
	}

	

}