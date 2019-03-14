package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the productstock database table.
 * 
 */
@Entity(name="ProductStock")
public class Productstock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idProductStock;

	private int stock;

	//bi-directional many-to-one association to Productinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="productId")
	private Productinfo productinfo;

	public Productstock() {
	}

	
	public long getIdProductstock() {
		return idProductStock;
	}


	public void setIdProductstock(long idProductstock) {
		this.idProductStock = idProductstock;
	}


	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Productinfo getProductinfo() {
		return this.productinfo;
	}

	public void setProductinfo(Productinfo productinfo) {
		this.productinfo = productinfo;
	}

}