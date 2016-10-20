package com.overseer.credentials;

public interface IPasswordHashGeneratorFactory {
	public IPasswordHashGenerator getPasswordHashCalculator(String passwordHashGeneratorName) throws PasswordHashGeneratorNotFoundException;
}
