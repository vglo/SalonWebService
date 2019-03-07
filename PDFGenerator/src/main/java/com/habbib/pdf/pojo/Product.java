/**
 * 
 */
package com.habbib.pdf.pojo;

/**
 * @author yash
 *
 */
public class Product {

	/** The id. */
    protected String billNumber;
    
    protected String  quantity;
    
    /** The name. */
    protected String name;
    
    /** The price. */
    protected String price;
    
    /** The vat. */
    protected String cgst;
    
    protected String sgst;
    
    protected String grandTtoal;
    
    protected String total;
    
    protected String discount;

	/**
	 * @return the billNumber
	 */
	public String getBillNumber() {
		return billNumber;
	}

	/**
	 * @param billNumber the billNumber to set
	 */
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the cgst
	 */
	public String getCgst() {
		return cgst;
	}

	/**
	 * @param cgst the cgst to set
	 */
	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	/**
	 * @return the sgst
	 */
	public String getSgst() {
		return sgst;
	}

	/**
	 * @param sgst the sgst to set
	 */
	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	/**
	 * @return the grandTtoal
	 */
	public String getGrandTtoal() {
		return grandTtoal;
	}

	/**
	 * @param grandTtoal the grandTtoal to set
	 */
	public void setGrandTtoal(String grandTtoal) {
		this.grandTtoal = grandTtoal;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [billNumber=" + billNumber + ", quantity=" + quantity + ", name=" + name + ", price=" + price
				+ ", cgst=" + cgst + ", sgst=" + sgst + ", grandTtoal=" + grandTtoal + ", total=" + total
				+ ", discount=" + discount + "]";
	}
    
    
    
     
}
