package com.habbib.staff.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.habbib.staff.util.Utilities;
import com.habib.utility.token.ITokenGenerator;
import com.habib.utility.token.TokenGeneratorByDateTime;

@Service
public class PasswordEncoder {
	
	
	public static boolean checkHash(String password,String hashedPassword) throws NoSuchAlgorithmException {
		if(null ==password)
			throw new NullPointerException();
		if(BCrypt.checkpw(password, hashedPassword))
			return true;
		return false;
	}
	
	public static final String getSalt() throws NoSuchAlgorithmException {
		 return BCrypt.gensalt(10,SecureRandom.getInstanceStrong());
	}
	
	public HttpHeaders getHeaderwithToken() {
		TokenGeneratorByDateTime token = new TokenGeneratorByDateTime();
		HttpHeaders header = new HttpHeaders();
		header.add("Oauth Token", token.getToken());
		return header;
	}

}
