package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.model.DashboardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class DashboardController {

    @FXML
    private FlowPane flowStats;

    @FXML
    private Label lblActiveUsers;

    @FXML
    private Label lblCategoryCount;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblDeliveries;

    @FXML
    private Label lblEmployeeCount;

    @FXML
    private Label lblInventoryCount;

    @FXML
    private Label lblLowStockCount;

    @FXML
    private Label lblMonthlySales;

    @FXML
    private Label lblPaymentCount;

    @FXML
    private Label lblPendingOrders;

    @FXML
    private Label lblPendingPayments;

    @FXML
    private Label lblProductsCount;

    @FXML
    private Label lblPromotionCount;

    @FXML
    private Label lblPurchaseOrders;

    @FXML
    private Label lblReturnCount;

    @FXML
    private Label lblRevenue;

    @FXML
    private Label lblSalesCount;

    @FXML
    private Label lblSupplierCount;

    @FXML
    private Label lblTodaySales;

    public void initialize(){
        loadCustomerCount();
        loadProductsCount();
        //loadInvoiceCount();
        loadSupplerCount();
    }

    public void loadProductsCount(){
        try {
            int count = DashboardModel.getProductsCount();
            lblProductsCount.setText(String.valueOf(count));
        } catch (Exception e) {
            lblProductsCount.setText("Error");
        }
    }

    public void loadCustomerCount(){
        try {
            int count = DashboardModel.getCustomerCount();
            lblCustomerCount.setText(String.valueOf(count));
        } catch (Exception e) {
            lblCustomerCount.setText("Error");
        }
    }

    public void loadSupplerCount(){
        try {
            int count = DashboardModel.getSupplierCount();
            lblSupplierCount.setText(String.valueOf(count));
        } catch (Exception e) {
            lblSupplierCount.setText("Error");
        }
    }

    public void loadInventoryCount(){
        try {

        } catch (Exception e) {

        }
    }

}

