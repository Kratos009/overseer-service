package com.overseer.credentials;

public interface IPasswordHashGenerator {
	public byte[] GeneratePasswordHash(final String password, final byte[] passwordSalt, final String hashAlgorithm,
                                       final int iterations, final int derivedKeyLength);
}
