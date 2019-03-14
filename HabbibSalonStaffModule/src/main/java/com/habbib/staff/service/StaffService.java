/**
 * 
 */
package com.habbib.staff.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.habbib.staff.request.model.StaffCrendentialRequest;
import com.habbib.utility.salt.SaltGenerator;
import com.habib.utility.hashcode.HashGenerator;

/**
 * @author yash
 *
 */
@Service
public class StaffService {
	
	
	public StaffCrendentialRequest  generateCredential(StaffCrendentialRequest inputCredential,String salt) throws NoSuchAlgorithmException {
		HashGenerator hash = new HashGenerator();
		inputCredential.setPassword(hash.generateCode(inputCredential.getPassword()+salt));
		return inputCredential;
	}

	public boolean checkHash(String password,String hashedPassword,String salt) throws NoSuchAlgorithmException {
		if(null ==password)
			throw new NullPointerException();
		HashGenerator hash = new HashGenerator();
		String pass = hash.generateCode(password+salt);
		if(pass.equals(hashedPassword))
			return true;
		return false;
	}
	
	public  String getSalt() throws NoSuchAlgorithmException {
		SaltGenerator salt = new SaltGenerator();
		 return salt.getSalt(10);
	}
	
	public String generatePass(String password,String salt) {
		HashGenerator hash = new HashGenerator();
		SaltGenerator salt1 = new SaltGenerator();
		
		return hash.generateCode(password+salt1.getSalt(10));
	}
	
	
}
