package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import com.kawaii.kawaiicount.services.AccountService;
import com.kawaii.kawaiicount.services.AccountService.UpdateProfileResult;
import com.kawaii.kawaiicount.services.AccountService.ChangeAccountTypeResult;
import com.kawaii.kawaiicount.services.AccountService.ChangePasswordResult;
import com.kawaii.kawaiicount.services.AccountService.DeleteAccountResult;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import com.kawaii.kawaiicount.utilities.ManagerPasswordHelper;
import com.kawaii.kawaiicount.utilities.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class SettingsPageController extends BaseMainPageController
{
    @FXML private AnchorPane managerPasswordPane;
    @FXML private Label changePasswordLabel;

    @FXML private Label currentAccountTypeLabel;
    @FXML private Button changeAccountTypeButton;

    @FXML private Label accountDetailsErrorMessage;
    @FXML private Label accountDeletionErrorMessage;

    @FXML private TextField usernameField;
    @FXML private TextField emailField;

    @FXML private PasswordField accountTypeConfirmationField;
    @FXML private PasswordField accountDeletionConfirmationField;

    @FXML private PasswordField oldManagerPasswordField;
    @FXML private PasswordField newManagerPasswordField;

    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;

    @FXML
    private void initialize()
    {
        super.initializePage();

        // DISABLE CHANGE MANAGER PASSWORD IF USER IS A WORKER
        if(Session.getUserType().equals("worker"))
        {
            changePasswordLabel.setTranslateY(60);
            managerPasswordPane.setDisable(true);
            managerPasswordPane.setVisible(false);

            currentAccountTypeLabel.setText("Your current account type: Worker");
            changeAccountTypeButton.setText("Change Account Type to Manager");
        }

        ActiveSidebarItem active = new ActiveSidebarItem(selectionIndicator, settingsButton, settingsPink);
        setupSidebar(active, List.of(
            new SidebarItem(inventoryButton, inventoryWhite, "inventory-page", 500, -425),
            new SidebarItem(menuButton, menuWhite, "menu-page", 500, -365),
            new SidebarItem(ordersButton, ordersWhite, "orders-page", 500, -305),
            new SidebarItem(analyticsButton, analyticsWhite, "analytics-page", 500, -245),
            new SidebarItem(signOutButton, signOutWhite, "title-page", 300, 60)
        ));
    }

    @FXML
    private void updateProfile()
    {
        AccountService service = new AccountService();
        UpdateProfileResult result = service.updateProfile(
            Session.getUserId(),
            usernameField.getText().trim(),
            emailField.getText().trim()
        );

        switch (result)
        {
            case NOTHING_TO_UPDATE -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "No changes provided.", 3000, 2000);
            case INVALID_EMAIL -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Invalid email format.", 3000, 2000);
            case USERNAME_TAKEN -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Username already taken.", 3000, 2000);
            case EMAIL_TAKEN -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Email already in use.", 3000, 2000);
            case SUCCESS -> signOutAndReturn();
        }
    }

    @FXML
    private void changeAccountType()
    {
        AccountService service = new AccountService();
        ChangeAccountTypeResult result = service.changeAccountType(
            Session.getUserId(),
            accountTypeConfirmationField.getText()
        );

        switch (result)
        {
            case EMPTY_FIELDS -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Please enter the manager password.", 3000, 2000);
            case WRONG_PASSWORD -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Incorrect manager password.", 3000, 2000);
            case SUCCESS -> signOutAndReturn();
        }
    }

    @FXML
    private void updateManagerPassword()
    {
        String oldPass = oldManagerPasswordField.getText();
        String newPass = newManagerPasswordField.getText();

        if (oldPass.isEmpty() || newPass.isEmpty())
        {
            AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Please fill in all fields.", 3000, 2000);
            return;
        }

        if (!oldPass.equals(Session.getManagerProvidedPW()))
        {
            AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Incorrect manager password.", 3000, 2000);
            return;
        }

        Session.setManagerProvidedPW(newPass);
        ManagerPasswordHelper.save(newPass);
        AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Manager password updated!", 3000, 2000);
    }

    @FXML
    private void changePassword()
    {
        AccountService service = new AccountService();
        ChangePasswordResult result = service.changePassword(
            Session.getUserId(),
            oldPasswordField.getText(),
            newPasswordField.getText()
        );

        switch (result)
        {
            case EMPTY_FIELDS -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Please fill in all fields.", 3000, 2000);
            case WRONG_PASSWORD -> AnimationHelper.showErrorMessage(accountDetailsErrorMessage, "Incorrect password.", 3000, 2000);
            case SUCCESS -> signOutAndReturn();
        }
    }

    @FXML
    private void deleteAccount()
    {
        AccountService service = new AccountService();
        DeleteAccountResult result = service.deleteAccount(
            Session.getUserId(),
            accountDeletionConfirmationField.getText()
        );

        switch (result)
        {
            case EMPTY_FIELDS -> AnimationHelper.showErrorMessage(accountDeletionErrorMessage, "Please enter your password.", 3000, 2000);
            case WRONG_PASSWORD -> AnimationHelper.showErrorMessage(accountDeletionErrorMessage, "Incorrect password.", 3000, 2000);
            case SUCCESS -> signOutAndReturn();
        }
    }

    private void signOutAndReturn()
    {
        try
        {
            App.setRoot("title-page", App.WIDTH, App.HEIGHT, true);
        }
        catch (IOException e)
        {
            System.out.println("Failed to switch scenes: " + e.getMessage());
        }
    }
}
