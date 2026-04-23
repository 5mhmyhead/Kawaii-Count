package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TitlePageController
{
    private static final String documentationLink = "https://docs.google.com/document/d/1PhIbAgGxeTrwMWUfY0cXW5aCrBaAwW2MkR0ELerZvIk/edit?usp=sharing";

    @FXML private AnchorPane parentContainer;
    @FXML private Label errorMessage;

    @FXML
    private void openDocumentation() throws IOException
    {
        // CHECK IF DESKTOP IS SUPPORTED ON THE CURRENT PLATFORM
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
        {
            try
            {
                // CREATE A URI OBJECT FOR THE DESIRED URL
                URI uri = new URI(documentationLink);
                // OPEN THE URI IN THE DEFAULT BROWSER
                Desktop.getDesktop().browse(uri);
            }
            catch(URISyntaxException e)
            {
                System.out.println("Error opening documentation link: " + e.getMessage());
            }
        }
        else
        {
            errorMessage.setText("Desktop browsing is not supported on this platform.");
        }
    }

    @FXML
    private void switchToLoginPage()
    {
        TranslateTransition transition = AnimationHelper.createSlideX(parentContainer, -768, 0, 1000);
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
