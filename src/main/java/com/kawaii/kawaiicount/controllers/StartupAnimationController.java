package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// PLAY A STARTUP ANIMATION ON FIRST OPEN
public class StartupAnimationController implements Initializable
{
    @FXML private AnchorPane mainPane;
    @FXML private AnchorPane circlePane;
    @FXML private ImageView wakuImage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // CREATES AN ANIMATION WHERE THE TITLE PAGE SLIDES INTO THE SCREEN ON STARTUP
        TranslateTransition transitionImg = new TranslateTransition();
        TranslateTransition transitionPane = new TranslateTransition();
        TranslateTransition transitionCircle = new TranslateTransition();

        transitionImg.setNode(wakuImage);

        transitionImg.setDelay(Duration.millis(500));
        transitionImg.setDuration(Duration.seconds(1));

        transitionImg.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transitionImg.setToX(-249);

        transitionPane.setNode(mainPane);

        transitionPane.setDelay(Duration.millis(500));
        transitionPane.setDuration(Duration.seconds(1));

        transitionPane.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transitionPane.setToX(-600);

        transitionCircle.setNode(circlePane);

        transitionCircle.setDelay(Duration.millis(500));
        transitionCircle.setDuration(Duration.seconds(1));

        transitionCircle.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transitionCircle.setToX(-500);

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
        App.setRoot("title-page");
    }
}
