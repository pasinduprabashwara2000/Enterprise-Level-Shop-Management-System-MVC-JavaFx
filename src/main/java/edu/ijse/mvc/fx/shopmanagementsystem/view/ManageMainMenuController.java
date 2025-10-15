package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;

public class ManageMainMenuController {

    @FXML
    private Button inventoryBtn;

    @FXML
    private Button categoryBtn;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button customersBtn;

    @FXML
    private Button suppliersBtn;

    @FXML
    private Label titleLabel;

    @FXML
    private Hyperlink tradeMarkLabel;

    @FXML
    public void initialize() {
        customersBtn.setOnAction(e -> loadUI("ManageCustomer"));
        suppliersBtn.setOnAction(e -> loadUI("ManageSupplier"));
        inventoryBtn.setOnAction(e -> loadUI("ManageInventory"));
        categoryBtn.setOnAction(e -> loadUI("ManageCategory"));
    }

    private void loadUI(String fxmlName) {
        try {
            String fxmlPath = "/edu/ijse/mvc/fx/shopmanagementsystem/" + fxmlName + ".fxml";
            URL fxmlLocation = getClass().getResource(fxmlPath);
            Parent root = FXMLLoader.load(fxmlLocation);
            contentPane.getChildren().setAll(root);

        } catch (IOException e) {
            System.err.println("Error loading FXML: " + fxmlName);
            e.printStackTrace();
        }
    }

}
