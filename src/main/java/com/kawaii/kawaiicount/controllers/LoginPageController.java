package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.utilities.TransitionHelper;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginPageController
{
    @FXML private AnchorPane parentContainer;

    @FXML
    private void switchToTitlePage()
    {
        TranslateTransition transition = TransitionHelper.createSlideX(parentContainer, 768, 0, 1000);
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
        TranslateTransition transition = TransitionHelper.createSlideY(parentContainer, -576, 0, 1000);
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
        TranslateTransition transition = TransitionHelper.createSlideY(parentContainer, 576, 0, 1000);
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
}
