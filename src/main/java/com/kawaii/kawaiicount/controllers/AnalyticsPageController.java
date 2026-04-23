package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import javafx.fxml.FXML;
import java.util.List;

public class AnalyticsPageController extends BaseMainPageController
{
    @FXML
    private void initialize()
    {
        super.initializePage();

        ActiveSidebarItem active = new ActiveSidebarItem(selectionIndicator, analyticsButton, analyticsPink);
        setupSidebar(active, List.of(
            new SidebarItem(inventoryButton, inventoryWhite, "inventory-page", 300, -180),
            new SidebarItem(menuButton, menuWhite, "menu-page", 300, -120),
            new SidebarItem(ordersButton, ordersWhite, "orders-page", 300, -60),
            new SidebarItem(settingsButton, settingsWhite, "settings-page", 500, 245),
            new SidebarItem(signOutButton, signOutWhite, "title-page", 500, 245)
        ));
    }
}
