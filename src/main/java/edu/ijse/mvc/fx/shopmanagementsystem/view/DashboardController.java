package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.DashboardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class DashboardController {

    @FXML
    private FlowPane flowTopProducts;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblInvoiceCount;

    @FXML
    private Label lblProductsCount;

    @FXML
    private Label lblRevenue;

    public void initialize(){
        loadCustomerCount();
        loadTopProducts();
    }

    public void loadProductsCount(){
        try {
            int count = DashboardModel.getProductsCount();
            lblProductsCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            lblProductsCount.setText("Error");
        }
    }

    public void loadCustomerCount(){
        try {
            int count = DashboardModel.getCustomerCount();
            lblCustomerCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            lblCustomerCount.setText("Error");
        }
    }

    private void loadTopProducts() {
        try {
            List<ProductDTO> topProducts = DashboardModel.getTopSellingProducts();

            for (ProductDTO product : topProducts) {
                VBox card = new VBox();
                card.setSpacing(5);
                card.setPrefWidth(200);
                card.setPrefHeight(120);
                card.getStyleClass().add("product-card");

                Label lblName = new Label(product.getName());
                lblName.getStyleClass().add("product-name");

                card.getChildren().addAll(lblName);

                flowTopProducts.getChildren().add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

