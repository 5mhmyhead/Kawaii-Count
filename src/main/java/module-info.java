module com.kawaii.kawaiicount {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.kawaii.kawaiicount to javafx.fxml;
    exports com.kawaii.kawaiicount;
    exports com.kawaii.kawaiicount.controllers;
    opens com.kawaii.kawaiicount.controllers to javafx.fxml;
}