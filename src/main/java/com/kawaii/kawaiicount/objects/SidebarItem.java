package com.kawaii.kawaiicount.objects;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public record SidebarItem(Button button, ImageView icon, String fxml, int duration, int toY) {
}
