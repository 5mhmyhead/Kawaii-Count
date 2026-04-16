package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class LoginPageController
{
    @FXML private AnchorPane parentContainer;

    @FXML
    private void switchToTitlePage()
    {
        TranslateTransition transition = new TranslateTransition();

        transition.setNode(parentContainer);
        transition.setDuration(Duration.seconds(1));

        transition.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transition.setToX(768);

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
}
