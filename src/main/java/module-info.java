module edu.ijse.mvc.fx.shopmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ijse.mvc.fx.shopmanagementsystem to javafx.fxml;
    exports edu.ijse.mvc.fx.shopmanagementsystem;
}