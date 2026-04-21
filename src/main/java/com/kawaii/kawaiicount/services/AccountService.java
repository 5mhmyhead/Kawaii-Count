package com.kawaii.kawaiicount.services;

import com.kawaii.kawaiicount.objects.Account;
import com.kawaii.kawaiicount.repositories.AccountRepository;
import com.kawaii.kawaiicount.utilities.Session;

public class AccountService
{
    private final AccountRepository repository = new AccountRepository();

    // ENUMS THAT HOLD THE DIFFERENT ERROR STATES
    public enum CreateAccountResult { SUCCESS, EMPTY_FIELDS, INVALID_EMAIL, INVALID_MANAGER_PASSWORD, USERNAME_TAKEN }
    public enum ResetPasswordResult { SUCCESS, EMPTY_FIELDS, INVALID_EMAIL, PASSWORDS_DO_NOT_MATCH, EMAIL_NOT_FOUND }

    public Account login(String username, String password)
    {
        if (username.isEmpty() || password.isEmpty()) return null;
        return repository.findByCredentials(username, password);
    }

    public CreateAccountResult createAccount(String username, String password, String email, String accountType, String managerPassword)
    {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty())
            return CreateAccountResult.EMPTY_FIELDS;

        if (!email.contains("@") || !email.contains("."))
            return CreateAccountResult.INVALID_EMAIL;

        if (accountType.equals("manager") && !managerPassword.equals(Session.getManagerProvidedPW()))
            return CreateAccountResult.INVALID_MANAGER_PASSWORD;

        if (repository.existsByUsername(username))
            return CreateAccountResult.USERNAME_TAKEN;

        boolean success = repository.createAccount(username, password, email, accountType);
        return success ? CreateAccountResult.SUCCESS : CreateAccountResult.EMPTY_FIELDS;
    }

    public ResetPasswordResult resetPassword(String email, String password, String confirmPassword)
    {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            return ResetPasswordResult.EMPTY_FIELDS;

        if (!email.contains("@") || !email.contains("."))
            return ResetPasswordResult.INVALID_EMAIL;

        if (!password.equals(confirmPassword))
            return ResetPasswordResult.PASSWORDS_DO_NOT_MATCH;

        if (!repository.existsByEmail(email))
            return ResetPasswordResult.EMAIL_NOT_FOUND;

        boolean success = repository.updatePassword(email, password);
        return success ? ResetPasswordResult.SUCCESS : ResetPasswordResult.EMAIL_NOT_FOUND;
    }
}
