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
    public enum UpdateProfileResult { SUCCESS, NOTHING_TO_UPDATE, INVALID_EMAIL, USERNAME_TAKEN, EMAIL_TAKEN }
    public enum ChangePasswordResult { SUCCESS, WRONG_PASSWORD, EMPTY_FIELDS }
    public enum DeleteAccountResult { SUCCESS, WRONG_PASSWORD, EMPTY_FIELDS }
    public enum ChangeAccountTypeResult { SUCCESS, WRONG_PASSWORD, EMPTY_FIELDS }

    public Account login(String username, String password)
    {
        if (username.isEmpty() || password.isEmpty())
            return null;

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

    public UpdateProfileResult updateProfile(int userId, String newUsername, String newEmail)
    {
        if (newUsername.isEmpty() && newEmail.isEmpty())
            return UpdateProfileResult.NOTHING_TO_UPDATE;

        if (!newEmail.isEmpty() && (!newEmail.contains("@") || !newEmail.contains(".")))
            return UpdateProfileResult.INVALID_EMAIL;

        if (!newUsername.isEmpty() && repository.existsByUsername(newUsername))
            return UpdateProfileResult.USERNAME_TAKEN;

        if (!newEmail.isEmpty() && repository.existsByEmail(newEmail))
            return UpdateProfileResult.EMAIL_TAKEN;

        if (!newUsername.isEmpty())
            repository.updateUsername(userId, newUsername);

        if (!newEmail.isEmpty())
            repository.updateEmail(userId, newEmail);

        return UpdateProfileResult.SUCCESS;
    }

    public ChangeAccountTypeResult changeAccountType(int userId, String managerPassword)
    {
        if (managerPassword.isEmpty())
            return ChangeAccountTypeResult.EMPTY_FIELDS;

        if (!managerPassword.equals(Session.getManagerProvidedPW()))
            return ChangeAccountTypeResult.WRONG_PASSWORD;

        String newType = Session.getUserType().equals("worker") ? "manager" : "worker";
        repository.updateAccountType(userId, newType);
        return ChangeAccountTypeResult.SUCCESS;
    }

    public ChangePasswordResult changePassword(int userId, String oldPassword, String newPassword)
    {
        if (oldPassword.isEmpty() || newPassword.isEmpty())
            return ChangePasswordResult.EMPTY_FIELDS;

        if (!repository.verifyPassword(userId, oldPassword))
            return ChangePasswordResult.WRONG_PASSWORD;

        boolean success = repository.updatePassword(
                repository.findEmailByUserId(userId), newPassword
        );
        return success ? ChangePasswordResult.SUCCESS : ChangePasswordResult.WRONG_PASSWORD;
    }

    public DeleteAccountResult deleteAccount(int userId, String password)
    {
        if (password.isEmpty())
            return DeleteAccountResult.EMPTY_FIELDS;

        if (!repository.verifyPassword(userId, password))
            return DeleteAccountResult.WRONG_PASSWORD;

        repository.deleteAccount(userId);
        return DeleteAccountResult.SUCCESS;
    }
}
