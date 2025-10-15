module edu.ijse.mvc.fx.shopmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    opens edu.ijse.mvc.fx.shopmanagementsystem.view to javafx.fxml;

    exports edu.ijse.mvc.fx.shopmanagementsystem;
    exports edu.ijse.mvc.fx.shopmanagementsystem.view;

}
