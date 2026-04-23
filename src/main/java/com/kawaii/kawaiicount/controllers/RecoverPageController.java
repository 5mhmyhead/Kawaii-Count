package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.services.AccountService;
import com.kawaii.kawaiicount.services.AccountService.ResetPasswordResult;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RecoverPageController
{
    @FXML private AnchorPane parentContainer;
    @FXML private Label errorMessage;

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;

    @FXML
    private void resetPassword() throws IOException
    {
        ResetPasswordResult result = getResetPasswordResult();

        switch (result)
        {
            case EMPTY_FIELDS -> AnimationHelper.showErrorMessage(errorMessage, "Please fill in all fields.", 3000, 2000);
            case INVALID_EMAIL -> AnimationHelper.showErrorMessage(errorMessage, "Invalid email format.", 3000, 2000);
            case PASSWORDS_DO_NOT_MATCH -> AnimationHelper.showErrorMessage(errorMessage, "Passwords do not match.", 3000, 2000);
            case EMAIL_NOT_FOUND -> AnimationHelper.showErrorMessage(errorMessage, "Email not found.", 3000, 2000);
            case SUCCESS -> switchToLoginPage();
        }
    }

    private ResetPasswordResult getResetPasswordResult()
    {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirm = confirmField.getText().trim();

        AccountService service = new AccountService();
        return service.resetPassword(email, password, confirm);
    }

    @FXML
    private void switchToLoginPage()
    {
        TranslateTransition transition = AnimationHelper.createSlideY(parentContainer, 576, 0, 1000);
        transition.play();

        transition.setOnFinished(_ -> {
            try
            {
                App.setRoot("login-page", App.WIDTH, App.HEIGHT, false);
            }
            catch (IOException e)
            {
                System.out.println("Failed to Switch Scenes: " + e.getMessage());
            }
        });
    }
}
