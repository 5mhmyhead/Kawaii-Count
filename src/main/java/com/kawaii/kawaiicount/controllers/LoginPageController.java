package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.objects.Account;
import com.kawaii.kawaiicount.services.AccountService;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginPageController
{
    @FXML private AnchorPane parentContainer;
    @FXML private Label errorMessage;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void login() throws IOException
    {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (user.isEmpty() || pass.isEmpty())
        {
            AnimationHelper.showErrorMessage(errorMessage, "Please fill in all fields.", 3000, 2000);
            return;
        }

        AccountService service = new AccountService();
        Account account = service.login(user, pass);

        if (account == null)
        {
            AnimationHelper.showErrorMessage(errorMessage, "Invalid username or password.", 3000, 2000);
            return;
        }

        switchToInventoryPage();
    }

    @FXML
    private void switchToTitlePage()
    {
        TranslateTransition transition = AnimationHelper.createSlideX(parentContainer, 768, 0, 1000);
        transition.play();

        transition.setOnFinished(_ -> {
            try
            {
                App.setRoot("title-page", App.WIDTH, App.HEIGHT);
            }
            catch (IOException e)
            {
                System.out.println("Failed to Switch Scenes: " + e.getMessage());
            }
        });
    }

    @FXML
    private void switchToRecoverPage()
    {
        TranslateTransition transition = AnimationHelper.createSlideY(parentContainer, -576, 0, 1000);
        transition.play();

        transition.setOnFinished(_ -> {
            try
            {
                App.setRoot("recover-page", App.WIDTH, App.HEIGHT);
            }
            catch (IOException e)
            {
                System.out.println("Failed to Switch Scenes: " + e.getMessage());
            }
        });
    }

    @FXML
    private void switchToCreateAccountPage()
    {
        TranslateTransition transition = AnimationHelper.createSlideY(parentContainer, 576, 0, 1000);
        transition.play();

        transition.setOnFinished(_ -> {
            try
            {
                App.setRoot("create-account-page", App.WIDTH, App.HEIGHT);
            }
            catch (IOException e)
            {
                System.out.println("Failed to Switch Scenes: " + e.getMessage());
            }
        });
    }

    private void switchToInventoryPage() throws IOException
    {
        App.setRoot("inventory-page", App.MAIN_WIDTH, App.MAIN_HEIGHT);
    }
}
