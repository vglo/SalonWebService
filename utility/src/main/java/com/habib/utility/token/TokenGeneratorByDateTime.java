package com.habib.utility.token;
import java.time.LocalDateTime;
import java.util.UUID;

import com.habib.utility.hashcode.HashGenerator;
import com.habib.utility.hashcode.ICodeGenerator;

public class TokenGeneratorByDateTime implements  ITokenGenerator{

		LocalDateTime localDateTime;
		ICodeGenerator hashGenerator;

		public TokenGeneratorByDateTime() {
			localDateTime = LocalDateTime.now();
			hashGenerator = new HashGenerator();
		}

		public LocalDateTime getLocalDateTime() { 
			return localDateTime;
		}

		public void setLocalDateTime(LocalDateTime localDateTime) {
			this.localDateTime = localDateTime;
		}

		public ICodeGenerator getHashGenerator() {
			return hashGenerator;
		}

		public void setHashGenerator(ICodeGenerator hashGenerator) {
			this.hashGenerator = hashGenerator;
		}

		public String getToken() {
			return hashGenerator.generateCode(localDateTime.toString() + UUID.randomUUID().toString());
		}

		public String getToken(String seed) {
			if (seed == null)
				seed = "";
			seed += localDateTime.toString() + UUID.randomUUID().toString();
			return hashGenerator.generateCode(seed);
		}

	}


