package com.habbib.utility.salt;

import java.util.Random;

public class SaltGenerator {

	public String getSalt(int len) {
		StringBuffer randomtext=new StringBuffer();
		Random random=new Random();
		int i=0;
		while(i<len) {
			int temp=random.nextInt(126);
			if(temp<32) {
				temp+=32;
			}
			char[] c=Character.toChars(temp);
			randomtext.append(c);
			i++;
		}
		String salt=randomtext.toString();
		return salt;
	}
}
