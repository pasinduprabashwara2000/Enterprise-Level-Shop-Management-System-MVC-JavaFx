package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.ProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.model.DashboardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

public class DashboardController {

    @FXML
    private TableColumn<ProductTM, Boolean> colActive;

    @FXML
    private TableColumn<ProductTM, Integer> colBarcode;

    @FXML
    private TableColumn<ProductTM, String> colCategoryID;

    @FXML
    private TableColumn<ProductTM, String> colName;

    @FXML
    private TableColumn<ProductTM, String> colProductID;

    @FXML
    private TableColumn<ProductTM, String> colSKU;

    @FXML
    private TableColumn<ProductTM, Double> colTaxRate;

    @FXML
    private TableColumn<ProductTM, String> colUnit;

    @FXML
    private TableColumn<ProductTM, Double> colUnitPrice;

    @FXML
    private TableColumn<ProductTM, Integer> colQty;

    @FXML
    private TableView<ProductTM> detailsTable;

    @FXML
    private FlowPane flowStats;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblInventoryCount;

    @FXML
    private Label lblProductsCount;

    @FXML
    private Label lblProfitCount;

    @FXML
    private Label lblReturnCount;

    @FXML
    private Label lblRevenue;

    @FXML
    private Label lblSalesCount;

    @FXML
    private Label lblSupplierCount;

    public void initialize(){
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colSKU.setCellValueFactory(new PropertyValueFactory<>("SKU"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTaxRate.setCellValueFactory(new PropertyValueFactory<>("taxRate"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("categoryID"));

        loadTable();
        loadCustomerCount();
        loadProductsCount();
        loadSaleCount();
        loadSupplerCount();
        loadInventoryCount();
        loadReturnCount();
        loadTotalRevenue();
        loadTotalProfit();
    }

    private void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(DashboardModel.topSellingProducts());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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

    public void loadInventoryCount(){
        try {
            int count = DashboardModel.getInventoryCount();
            lblInventoryCount.setText(String.valueOf(count));
        } catch (Exception e) {
            lblInventoryCount.setText("Error");
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

    public void loadTotalRevenue(){
        try {
            double price = DashboardModel.calculateTotalRevenue();
            lblRevenue.setText(String.valueOf(price));
        } catch (Exception e){
            lblRevenue.setText("Error");
        }
    }

    public void loadTotalProfit(){
        try {
            double profit = DashboardModel.calculateTotalProfit();
            lblProfitCount.setText(String.valueOf(profit));
        } catch (Exception e) {
            lblProfitCount.setText("Error");
        }
    }

}

