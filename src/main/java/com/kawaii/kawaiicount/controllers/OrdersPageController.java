package com.kawaii.kawaiicount.controllers;

import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import javafx.fxml.FXML;
import java.util.List;

public class OrdersPageController extends BaseMainPageController
{
    @FXML
    private void initialize()
    {
        super.initializePage();

        ActiveSidebarItem active = new ActiveSidebarItem(selectionIndicator, ordersButton, ordersPink);
        setupSidebar(active, List.of(
            new SidebarItem(inventoryButton, inventoryWhite, "inventory-page", 300, -120),
            new SidebarItem(menuButton, menuWhite, "menu-page", 300, -60),
            new SidebarItem(analyticsButton, analyticsWhite, "analytics-page", 300, 60),
            new SidebarItem(settingsButton, settingsWhite, "settings-page", 500, 305),
            new SidebarItem(signOutButton, signOutWhite, "title-page", 500, 365)
        ));
    }
}
