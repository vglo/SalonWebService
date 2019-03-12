package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the moduleinfo database table.
 * 
 */
@Entity(name="ModuleInfo")
@NamedQuery(name="ModuleInfo.findAll", query="SELECT m FROM ModuleInfo m")
public class Moduleinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idModuleInfo;

	private String name;

	//bi-directional many-to-many association to Shopinfo
	@JsonIgnore
	@ManyToMany(mappedBy="moduleinfos")
	private List<Shopinfo> shopinfos;

	public Moduleinfo() {
	}

	public int getIdModuleInfo() {
		return this.idModuleInfo;
	}

	public void setIdModuleInfo(int idModuleInfo) {
		this.idModuleInfo = idModuleInfo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shopinfo> getShopinfos() {
		return this.shopinfos;
	}

	public void setShopinfos(List<Shopinfo> shopinfos) {
		this.shopinfos = shopinfos;
	}

}