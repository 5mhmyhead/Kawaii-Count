package com.kawaii.kawaiicount.objects;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public record ActiveSidebarItem(AnchorPane selectionIndicator, Button button, ImageView icon) {}
