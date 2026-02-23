package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Button utilitiesBtn;

    @FXML
    private Button placeOrderBtn;

    @FXML
    private Button returnBtn;

    @FXML
    public void initialize() {
        dashboardBtn.setOnAction(event -> loadUI("Dashboard2"));
        utilitiesBtn.setOnAction(event -> loadUI("Utilities"));
        placeOrderBtn.setOnAction(event -> loadUI("ManageOrder"));
        searchProducts.setOnAction(event -> loadUI("ManageProducts2"));
        customerBtn.setOnAction(event -> loadUI("ManageCustomer"));
        paymentBtn.setOnAction(event -> loadUI("ManagePayment"));
        returnBtn.setOnAction(event -> loadUI("ManageReturn"));
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

    public void navigateLogOut(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = (Stage) logOutBtn.getScene().getWindow();

            Scene scene = new Scene(
                    FXMLLoader.load(getClass().getResource(
                            "/edu/ijse/mvc/fx/shopmanagementsystem/ManageLogin.fxml"
                    ))
            );

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            new Alert(Alert.AlertType.INFORMATION,"Logout Successfully !").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}