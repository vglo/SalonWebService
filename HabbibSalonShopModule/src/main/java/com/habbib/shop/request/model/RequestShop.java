/**
 * 
 */
package com.habbib.shop.request.model;

/**
 * @author yash
 *
 */
public class RequestShop {


	private String address;

	private String name;

	private String phone1;

	private String phone2;

	private int type;
	
	private int parentShop;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the parentShop
	 */
	public int getParentShop() {
		return parentShop;
	}

	/**
	 * @param parentShop the parentShop to set
	 */
	public void setParentShop(int parentShop) {
		this.parentShop = parentShop;
	}
	
	
	
}
