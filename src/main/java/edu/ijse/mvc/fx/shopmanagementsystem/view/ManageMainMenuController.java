package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;

public class ManageMainMenuController {

    @FXML
    private Button categoryBtn;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button customersBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button inventoryBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private VBox menuVBox;

    @FXML
    private Button paymentBtn;

    @FXML
    private Button productsBtn;

    @FXML
    private Button promotionBtn;

    @FXML
    private Button purchaseOrderBtn;

    @FXML
    private Button purchaseOrderProductBtn;

    @FXML
    private Button reportsBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private Button returnproductBtn;

    @FXML
    private Button roleBtn;

    @FXML
    private Button saleBtn;

    @FXML
    private Button saleProductBtn;

    @FXML
    private Button suppliersBtn;

    @FXML
    private Button supplyBtn;

    @FXML
    private Hyperlink tradeMarkLabel;

    @FXML
    private Button userBtn;

    @FXML
    public void initialize() {
        dashboardBtn.setOnAction(event -> loadUI("Dashboard"));
        roleBtn.setOnAction(e -> loadUI("ManageRole"));
        userBtn.setOnAction(e -> loadUI("ManageUser"));
        customersBtn.setOnAction(e -> loadUI("ManageCustomer"));
        categoryBtn.setOnAction(e -> loadUI("ManageCategory"));
        inventoryBtn.setOnAction(e -> loadUI("ManageInventory"));
        productsBtn.setOnAction(event -> loadUI("ManageProducts"));
        suppliersBtn.setOnAction(e -> loadUI("ManageSupplier"));
        supplyBtn.setOnAction(event -> loadUI("ManageSupply"));
        promotionBtn.setOnAction(event -> loadUI("ManagePromotion"));
        saleBtn.setOnAction(event -> loadUI("ManageSale"));
        saleProductBtn.setOnAction(event -> loadUI("ManageSaleProduct"));
        paymentBtn.setOnAction(event -> loadUI("ManagePayment"));
        returnBtn.setOnAction(event -> loadUI("ManageReturn"));
        returnproductBtn.setOnAction(event -> loadUI("ManageReturnProduct"));

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
