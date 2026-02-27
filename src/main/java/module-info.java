module edu.ijse.mvc.fx.shopmanagementsystem {

    // -------------------- JavaFX --------------------
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    // -------------------- Database --------------------
    requires java.sql;

    // -------------------- Compile-time Libraries --------------------
    requires static lombok;

    // -------------------- Desktop / Reporting --------------------
    requires java.desktop;
    requires net.sf.jasperreports.core;

    // -------------------- JSON & XML --------------------
    requires org.json;

    // -------------------- Open Packages (Reflection Access) --------------------
    opens edu.ijse.mvc.fx.shopmanagementsystem.model to javafx.base;
    opens edu.ijse.mvc.fx.shopmanagementsystem.DTO to javafx.base;
    opens edu.ijse.mvc.fx.shopmanagementsystem.controller to javafx.fxml;

    // -------------------- Exported Packages --------------------
    exports edu.ijse.mvc.fx.shopmanagementsystem;
    exports edu.ijse.mvc.fx.shopmanagementsystem.controller;
}