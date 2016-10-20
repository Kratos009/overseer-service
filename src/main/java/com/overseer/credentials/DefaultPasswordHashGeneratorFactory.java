package com.overseer.credentials;


public class DefaultPasswordHashGeneratorFactory implements IPasswordHashGeneratorFactory {

    public IPasswordHashGenerator getPasswordHashCalculator(String passwordHashGeneratorName)
            throws PasswordHashGeneratorNotFoundException {
        switch (passwordHashGeneratorName) {
            case "PBKDF2":
                return new PBKDF2PasswordHashGenerator();
            default:
                throw new PasswordHashGeneratorNotFoundException();
        }
    }

}
