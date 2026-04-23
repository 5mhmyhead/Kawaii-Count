package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.services.AccountService;
import com.kawaii.kawaiicount.services.AccountService.CreateAccountResult;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccountPageController
{
    @FXML private AnchorPane parentContainer;
    @FXML private AnchorPane buttonContainer;
    @FXML private AnchorPane bottomContainer;
    @FXML private AnchorPane managerContainer;

    @FXML private Button managerButton;
    @FXML private Button workerButton;
    @FXML private Label errorMessage;

    @FXML private PasswordField managerPasswordField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField emailField;

    @FXML
    private void createAccount()
    {
        CreateAccountResult result = getCreateAccountResult();

        switch (result)
        {
            case EMPTY_FIELDS -> AnimationHelper.showErrorMessage(errorMessage, "Please fill in all fields.", 3000, 2000);
            case INVALID_EMAIL -> AnimationHelper.showErrorMessage(errorMessage, "Invalid email format.", 3000, 2000);
            case INVALID_MANAGER_PASSWORD -> AnimationHelper.showErrorMessage(errorMessage, "Invalid manager password.", 3000, 2000);
            case USERNAME_TAKEN -> AnimationHelper.showErrorMessage(errorMessage, "Username already exists.", 3000, 2000);
            case SUCCESS -> switchToLoginPage();
        }

    }

    private CreateAccountResult getCreateAccountResult()
    {
        // USER IS CREATING A MANAGER ACCOUNT IF MANAGER BUTTON IS DISABLED
        boolean isManagerView = managerButton.isDisabled();

        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        String managerPassword = managerPasswordField.getText();
        String accountType = isManagerView ? "manager" : "worker";

        AccountService service = new AccountService();
        return service.createAccount(username, password, email, accountType, managerPassword);
    }

    @FXML
    private void switchToManagerView()
    {
        managerContainer.setVisible(true);

        TranslateTransition buttonTransition = AnimationHelper.createSlideY(buttonContainer, 0, 0, 500);
        TranslateTransition bottomTransition = AnimationHelper.createSlideY(bottomContainer, 0, 0, 500);
        FadeTransition fade = AnimationHelper.createFade(managerContainer, 0, 1, 200, 300);

        buttonTransition.play();
        bottomTransition.play();
        fade.play();

        managerButton.setDisable(true);
        workerButton.setDisable(false);
    }

    @FXML
    private void switchToWorkerView()
    {
        TranslateTransition buttonTransition = AnimationHelper.createSlideY(buttonContainer, 36, 0, 500);
        TranslateTransition bottomTransition = AnimationHelper.createSlideY(bottomContainer, -36, 0, 500);

        FadeTransition fade = AnimationHelper.createFade(managerContainer, 1, 0, 0, 300);
        fade.setOnFinished(_ -> managerContainer.setVisible(false));

        buttonTransition.play();
        bottomTransition.play();
        fade.play();

        workerButton.setDisable(true);
        managerButton.setDisable(false);
    }

    @FXML
    private void switchToLoginPage()
    {
        TranslateTransition transition = AnimationHelper.createSlideY(parentContainer, -576, 0, 1000);
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
