package com.overseer.credentials;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PBKDF2PasswordHashGenerator implements IPasswordHashGenerator {
	@Override
	public byte[] GeneratePasswordHash(final String password, final byte[] passwordSalt, final String hashAlgorithm,
			final int iterations, final int derivedKeyLength) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmac" + hashAlgorithm);
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), passwordSalt, iterations, derivedKeyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			return res;

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}
}
