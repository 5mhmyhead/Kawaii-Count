package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
        TranslateTransition transitionImg = createTransition(wakuImage, -250);
        TranslateTransition transitionPane = createTransition(mainPane, -600);
        TranslateTransition transitionCircle = createTransition(circlePane, -500);

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

    // HELPER FUNCTION TO CREATE A RIGHT TO LEFT SLIDE TRANSITION
    private TranslateTransition createTransition(Node node, double toX)
    {
        TranslateTransition transition = new TranslateTransition();

        transition.setNode(node);
        transition.setDelay(Duration.millis(500));
        transition.setDuration(Duration.seconds(1));

        transition.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transition.setToX(toX);

        return transition;
    }

    private void switchToTitlePage() throws IOException
    {
        App.setRoot("title-page", App.WIDTH, App.HEIGHT);
    }
}
