package com.overseer.credentials;

public class UserCredentials {
	private String uuid;
	private String username;
	private byte[] passwordHash;
	private byte[] passwordSalt;
	private String passwordKeyDerivationFunction;
	private String passwordHashAlgorithm;
	private int passwordHashIterationCount;
	private int passwordHashDerivedKeyLength;
	private int passwordSaltLength;

	public UserCredentials() {
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(byte[] passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordKeyDerivationFunction() {
		return passwordKeyDerivationFunction;
	}

	public void setPasswordKeyDerivationFunction(String passwordKeyDerivationFunction) {
		this.passwordKeyDerivationFunction = passwordKeyDerivationFunction;
	}

	public String getPasswordHashAlgorithm() {
		return passwordHashAlgorithm;
	}

	public void setPasswordHashAlgorithm(String passwordHashAlgorithm) {
		this.passwordHashAlgorithm = passwordHashAlgorithm;
	}

	public int getPasswordHashIterationCount() {
		return passwordHashIterationCount;
	}

	public void setPasswordHashIterationCount(int passwordHashIterationCount) {
		this.passwordHashIterationCount = passwordHashIterationCount;
	}

	public int getPasswordHashDerivedKeyLength() {
		return passwordHashDerivedKeyLength;
	}

	public void setPasswordHashDerivedKeyLength(int passwordHashDerivedKeyLength) {
		this.passwordHashDerivedKeyLength = passwordHashDerivedKeyLength;
	}

	public int getPasswordSaltLength() {
		return passwordSaltLength;
	}

	public void setPasswordSaltLength(int passwordSaltLength) {
		this.passwordSaltLength = passwordSaltLength;
	}

}
