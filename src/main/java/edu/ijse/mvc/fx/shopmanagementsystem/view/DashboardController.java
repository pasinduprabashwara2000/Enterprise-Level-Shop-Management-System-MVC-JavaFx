package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.model.DashboardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

public class DashboardController {

    @FXML
    private TableColumn<?, ?> colActive;

    @FXML
    private TableColumn<?, ?> colBarcode;

    @FXML
    private TableColumn<?, ?> colCategoryID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProductID;

    @FXML
    private TableColumn<?, ?> colSKU;

    @FXML
    private TableColumn<?, ?> colTaxRate;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<?> detailsTable;

    @FXML
    private FlowPane flowStats;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblInventoryCount;

    @FXML
    private Label lblProductsCount;

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

    public void initialize(){
        loadCustomerCount();
        loadProductsCount();
        loadSaleCount();
        loadSupplerCount();
        loadPurchaseCount();
        loadReturnCount();
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

    public void loadSaleCount(){
        try {
            int count = DashboardModel.getSaleCount();
            lblSalesCount.setText(String.valueOf(count));
        } catch (Exception e) {
            lblSalesCount.setText("Error");
        }
    }

    public void loadPurchaseCount(){
        try {
            int count = DashboardModel.getPurchaseOrdersCount();
            lblPurchaseOrders.setText(String.valueOf(count));
        } catch (Exception e) {
            lblPurchaseOrders.setText("Error");
        }
    }

    public void loadReturnCount(){
        try {
            int count = DashboardModel.getReturnCount();
            lblReturnCount.setText(String.valueOf(count));
        } catch (Exception e) {
            lblReturnCount.setText("Error");
        }
    }

}

