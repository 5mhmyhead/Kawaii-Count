package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import com.kawaii.kawaiicount.utilities.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

// BASE CLASS FOR MAIN VIEWS THAT HANDLES SLIDING ANIMATION OF SIDEBAR
public abstract class BaseMainPageController
{
    @FXML protected AnchorPane selectionIndicator;
    @FXML protected Label welcomeMessage;

    @FXML protected Button inventoryButton;
    @FXML protected ImageView inventoryPink;
    @FXML protected ImageView inventoryWhite;

    @FXML protected Button menuButton;
    @FXML protected ImageView menuPink;
    @FXML protected ImageView menuWhite;

    @FXML protected Button ordersButton;
    @FXML protected ImageView ordersPink;
    @FXML protected ImageView ordersWhite;

    @FXML protected AnchorPane analyticsPane;
    @FXML protected Button analyticsButton;
    @FXML protected ImageView analyticsPink;
    @FXML protected ImageView analyticsWhite;

    @FXML protected Button settingsButton;
    @FXML protected ImageView settingsPink;
    @FXML protected ImageView settingsWhite;

    @FXML protected Button signOutButton;
    @FXML protected ImageView signOutPink;
    @FXML protected ImageView signOutWhite;

    protected void initializePage()
    {
        welcomeMessage.setText("Welcome, " + Session.getUsername() + "!");

        // DISABLE ANALYTICS PAGE IF USER IS A WORKER
        if(Session.getUserType().equals("worker"))
        {
            analyticsPane.setDisable(true);
            analyticsPane.setVisible(false);
        }
    }

    protected void setupSidebar(ActiveSidebarItem active, List<SidebarItem> targets)
    {
        for (SidebarItem target : targets)
            AnimationHelper.setupSidebarButton(active, target, targets);
    }
}
