package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.utilities.TransitionHelper;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccountPageController
{
    @FXML private AnchorPane parentContainer;

    @FXML
    private void switchToLoginPage()
    {
        TranslateTransition transition = TransitionHelper.createSlideY(parentContainer, -576, 0, 1000);
        transition.play();

        transition.setOnFinished(_ -> {
            try
            {
                App.setRoot("login-page", App.WIDTH, App.HEIGHT);
            }
            catch (IOException e)
            {
                System.out.println("Failed to Switch Scenes: " + e.getMessage());
            }
        });
    }
}
