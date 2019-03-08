/**
 * 
 */
package com.habib.utility.token;

/**
 * @author yash
 *
 */
public interface ITokenGenerator {

	public String getToken();
	
	public String getToken(String seed);
}
