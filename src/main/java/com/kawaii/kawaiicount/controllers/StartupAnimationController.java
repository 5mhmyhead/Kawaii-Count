package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.utilities.TransitionHelper;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

// PLAY A STARTUP ANIMATION ON FIRST OPEN
public class StartupAnimationController
{
    @FXML private AnchorPane mainPane;
    @FXML private AnchorPane circlePane;
    @FXML private ImageView wakuImage;

    @FXML
    private void initialize()
    {
        // CREATES AN ANIMATION WHERE THE TITLE PAGE SLIDES INTO THE SCREEN ON STARTUP
        TranslateTransition transitionImg = TransitionHelper.createSlideX(wakuImage, -250, 500, 1000);
        TranslateTransition transitionPane = TransitionHelper.createSlideX(mainPane, -600, 500, 1000);
        TranslateTransition transitionCircle = TransitionHelper.createSlideX(circlePane, -500, 500, 1000);

        transitionImg.play();
        transitionPane.play();
        transitionCircle.play();

        transitionPane.setOnFinished(_ -> {
            try
            {
                switchToTitlePage();
            }
            catch (IOException e)
            {
                System.out.println("Failed to Switch Scenes: " + e.getMessage());
            }
        });
    }

    private void switchToTitlePage() throws IOException
    {
        App.setRoot("title-page", App.WIDTH, App.HEIGHT);
    }
}
