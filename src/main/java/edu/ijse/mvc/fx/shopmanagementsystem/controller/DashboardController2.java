package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.model.DashboardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DashboardController2 {

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
    private TableColumn<ProductTM, Double> colUnitPrice;

    @FXML
    private TableColumn<ProductTM, Integer> colQty;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblPaymentCount;

    @FXML
    private Label lblReturnCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblCustomersCount;

    @FXML
    private TableView<ProductTM> detailsTable;

    @FXML
    void initialize() throws Exception {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colSKU.setCellValueFactory(new PropertyValueFactory<>("SKU"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("totalQty"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("categoryID"));

        loadTable();
        loadCustomerCount();
        loadOrdersCount();
        loadPaymentCount();
        loadReturnCount();

    }

    private void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(DashboardModel.topSellingProducts());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadCustomerCount() throws Exception {
        try {
            int count = DashboardModel.getCustomerCount();
            lblCustomersCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadOrdersCount() throws Exception {
        try {
            int count = DashboardModel.getOrderCount();
            lblOrderCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadPaymentCount() throws Exception {
        try {
            int count = DashboardModel.getPaymentCount();
            lblPaymentCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadReturnCount() throws Exception {
        try {
            int count = DashboardModel.getReturnCount();
            lblReturnCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


}

