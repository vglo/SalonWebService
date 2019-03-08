/**
 * 
 */
package com.habib.utility.hashcode;

import java.util.UUID;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;


/**
 * @author yash
 *
 */
public class HashGenerator implements ICodeGenerator {

	/* (non-Javadoc)
	 * @see com.habib.utility.hashcode.ICodeGenerator#generateCode(java.lang.String)
	 */
	public String generateCode(String data) {
		if (data == null)
			data = UUID.randomUUID().toString();
		DigestSHA3 digestSHA3 = new DigestSHA3(512);
		digestSHA3.update(data.getBytes());
		byte[] digest = digestSHA3.digest();
		String hashId = Hex.toHexString(digest);
		return hashId;
	}

}
