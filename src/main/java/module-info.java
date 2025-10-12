module edu.ijse.mvc.fx.shopmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens edu.ijse.mvc.fx.shopmanagementsystem to javafx.fxml;
    exports edu.ijse.mvc.fx.shopmanagementsystem;
}