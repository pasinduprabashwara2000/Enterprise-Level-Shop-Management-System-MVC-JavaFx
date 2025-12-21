package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.model.DashboardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DashboardController2 {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCustomers;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblPaymentCount;

    @FXML
    private Label lblReturnCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblSalesCount;

    @FXML
    private TableView<?> ordersTable;

    @FXML
    void initialize() throws Exception {

        loadSaleCount();
        loadOrdersCount();
        loadPaymentCount();
        loadReturnCount();

    }

    private void loadSaleCount() throws Exception {
        try {
            int count = DashboardModel.getSaleCount();
            lblSalesCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadOrdersCount() throws Exception {
        try {

        } catch (Exception e) {

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

