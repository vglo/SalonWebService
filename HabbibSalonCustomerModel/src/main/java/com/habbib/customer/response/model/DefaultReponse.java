package com.habbib.customer.response.model;

public class DefaultReponse<T> {
	
	private int responseCode;
	
	private String responseMsg;
	
	private T reponseObj;

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMsg
	 */
	public String getResponseMsg() {
		return responseMsg;
	}

	/**
	 * @param responseMsg the responseMsg to set
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	/**
	 * @return the reponseObj
	 */
	public T getReponseObj() {
		return reponseObj;
	}

	/**
	 * @param reponseObj the reponseObj to set
	 */
	public void setReponseObj(T reponseObj) {
		this.reponseObj = reponseObj;
	}
	
	

}
