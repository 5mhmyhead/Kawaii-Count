package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import com.kawaii.kawaiicount.utilities.AnimationHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class ordersPageController
{
    @FXML private AnchorPane selectionIndicator;
    @FXML private Label welcomeMessage;

    @FXML private Button inventoryButton;
    @FXML private ImageView inventoryPink;
    @FXML private ImageView inventoryWhite;

    @FXML private Button menuButton;
    @FXML private ImageView menuPink;
    @FXML private ImageView menuWhite;

    @FXML private Button ordersButton;
    @FXML private ImageView ordersPink;
    @FXML private ImageView ordersWhite;

    @FXML private Button analyticsButton;
    @FXML private ImageView analyticsPink;
    @FXML private ImageView analyticsWhite;

    @FXML private Button settingsButton;
    @FXML private ImageView settingsPink;
    @FXML private ImageView settingsWhite;

    @FXML private Button signOutButton;
    @FXML private ImageView signOutPink;
    @FXML private ImageView signOutWhite;

    @FXML
    private void initialize()
    {
        ActiveSidebarItem active = new ActiveSidebarItem(selectionIndicator, ordersButton, ordersPink);

        List<SidebarItem> targets = List.of(
                new SidebarItem(inventoryButton, inventoryWhite, "inventory-page", 300, -120),
                new SidebarItem(menuButton, menuWhite, "menu-page", 350, -60),
                new SidebarItem(analyticsButton, analyticsWhite, "analytics-page", 400, 60),
                new SidebarItem(settingsButton, settingsWhite, "settings-page", 450, 305),
                new SidebarItem(signOutButton, signOutWhite, "title-page", 500, 305)
        );

        for (SidebarItem target : targets)
            AnimationHelper.setupSidebarButton(active, target);
    }
}
