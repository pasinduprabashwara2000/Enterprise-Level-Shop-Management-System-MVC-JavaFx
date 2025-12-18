package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;

public class ManageMainMenu2Controller {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button searchProducts;

    @FXML
    private Button customerBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button paymentBtn;

    @FXML
    private Button placeOrderBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private Button saleBtn;

    @FXML
    public void initialize() {
        dashboardBtn.setOnAction(event -> loadUI("Dashboard2"));
        placeOrderBtn.setOnAction(event -> loadUI("ManagePlaceOrder"));
        searchProducts.setOnAction(event -> loadUI("ManageProducts2"));
        customerBtn.setOnAction(event -> loadUI("ManageCustomer"));
        saleBtn.setOnAction(event -> loadUI("ManageSale"));
        paymentBtn.setOnAction(event -> loadUI("ManagePayment"));
        returnBtn.setOnAction(event -> loadUI("ManageReturn"));
        logOutBtn.setOnAction(event -> loadUI(""));

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