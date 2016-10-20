package com.overseer.credentials;

public interface ICredentialService {

    public String saveNewCredentials(String username, String password) throws PasswordHashGeneratorNotFoundException;

    public String authenticateCredentials(String username, String password)
            throws PasswordHashGeneratorNotFoundException, IncorrectPasswordException, UserCredentialsNotFoundException;

    public void changePassword(String username, String currentPassword, String newPassword)
            throws PasswordHashGeneratorNotFoundException, IncorrectPasswordException,
            NewPasswordSameAsCurrentPasswordException, UserCredentialsNotFoundException;

    public void resetPassword(String username, String newPassword) throws PasswordHashGeneratorNotFoundException, UserCredentialsNotFoundException;

}
