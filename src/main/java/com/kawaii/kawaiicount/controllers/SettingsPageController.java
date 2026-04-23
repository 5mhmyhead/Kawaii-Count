package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import javafx.fxml.FXML;
import java.util.List;

public class SettingsPageController extends BaseMainPageController
{
    @FXML
    private void initialize()
    {
        super.initializePage();

        ActiveSidebarItem active = new ActiveSidebarItem(selectionIndicator, settingsButton, settingsPink);
        setupSidebar(active, List.of(
            new SidebarItem(inventoryButton, inventoryWhite, "inventory-page", 500, -425),
            new SidebarItem(menuButton, menuWhite, "menu-page", 500, -365),
            new SidebarItem(ordersButton, ordersWhite, "orders-page", 500, -305),
            new SidebarItem(analyticsButton, analyticsWhite, "analytics-page", 500, -245),
            new SidebarItem(signOutButton, signOutWhite, "title-page", 300, 60)
        ));
    }
}
