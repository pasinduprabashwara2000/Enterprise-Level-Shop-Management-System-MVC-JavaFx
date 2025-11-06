module edu.ijse.mvc.fx.shopmanagementsystem {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires java.sql;
    requires static lombok;

    opens edu.ijse.mvc.fx.shopmanagementsystem.view to javafx.fxml;
    opens edu.ijse.mvc.fx.shopmanagementsystem.model to javafx.base;
    opens edu.ijse.mvc.fx.shopmanagementsystem.DTO to javafx.base;

    exports edu.ijse.mvc.fx.shopmanagementsystem;
    exports edu.ijse.mvc.fx.shopmanagementsystem.view;
}
