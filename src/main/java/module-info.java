module com.kawaii.kawaiicount {
    requires org.xerial.sqlitejdbc;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.kawaii.kawaiicount to javafx.fxml;
    exports com.kawaii.kawaiicount;
    exports com.kawaii.kawaiicount.controllers;
    exports com.kawaii.kawaiicount.objects;
    opens com.kawaii.kawaiicount.controllers to javafx.fxml;
}