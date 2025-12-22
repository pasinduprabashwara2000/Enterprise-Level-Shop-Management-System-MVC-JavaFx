package edu.ijse.mvc.fx.shopmanagementsystem.view;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PaymentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePaymentController {

    private final PaymentController paymentController = new PaymentController();
    private final CustomerController customerController = new CustomerController();

    @FXML
    private TextField amountTxt;

    @FXML
    private TableColumn<PaymentDTO, Double> colAmount;

    @FXML
    private TableColumn<PaymentDTO, String> colMethod;

    @FXML
    private TableColumn<PaymentDTO, String> colPaymentId;

    @FXML
    private TableColumn<PaymentDTO, LocalDate> colReceivedAt;

    @FXML
    private TableColumn<PaymentDTO, String> colReference;

    @FXML
    private TableColumn<PaymentDTO, String> colCustomerId;

    @FXML
    private TableView<PaymentDTO> detailsTable;

    @FXML
    private ComboBox<String> paymentMethodCombo;

    @FXML
    private TextField paymentIdTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField referenceTxt;

    @FXML
    private ComboBox<String> customerIdCombo;

    @FXML
    void initialize() throws Exception {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colReceivedAt.setCellValueFactory(new PropertyValueFactory<>("receivedAt"));

        loadTable();
        loadCustomerIdThread();

        detailsTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });

    }

    private void loadSelectedRow(){
        PaymentDTO selectedPayment = detailsTable.getSelectionModel().getSelectedItem();

        if (selectedPayment != null){
            paymentIdTxt.setText(selectedPayment.getPaymentID());
            customerIdCombo.setValue(selectedPayment.getCustomerID());
            paymentMethodCombo.setValue(selectedPayment.getMethod());
            amountTxt.setText(String.valueOf(selectedPayment.getAmount()));
            referenceTxt.setText(selectedPayment.getReference());
            datePicker.setValue(selectedPayment.getReceivedAt());
        }
    }

    private void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(paymentController.getAllPayments());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadCustomerIdThread() throws Exception {
            Task <ObservableList<String>> task = new Task<>() {

                ArrayList <CustomerDTO> customers = customerController.getAllCustomers();
                @Override
                protected ObservableList<String> call() throws Exception {
                    return FXCollections.observableArrayList(customers.stream().map(CustomerDTO::getCustomerId).toList());
                }
            };
            task.setOnSucceeded(event -> customerIdCombo.setItems(task.getValue()));
            task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
            new Thread(task).start();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    null,
                    customerIdCombo.getValue(),
                    paymentMethodCombo.getValue(),
                    Double.parseDouble(amountTxt.getText()),
                    referenceTxt.getText(),
                    datePicker.getValue()
            );
            new Alert(Alert.AlertType.INFORMATION, paymentController.savePayment(paymentDTO)).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    paymentIdTxt.getText(),
                    customerIdCombo.getValue(),
                    paymentMethodCombo.getValue(),
                    Double.parseDouble(amountTxt.getText()),
                    referenceTxt.getText(),
                    datePicker.getValue()
            );
            new Alert(Alert.AlertType.INFORMATION, paymentController.updatePayment(paymentDTO)).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            new Alert(Alert.AlertType.INFORMATION, paymentController.deletePayment(paymentIdTxt.getText())).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        customerIdCombo.setValue(null);
        paymentIdTxt.setText("");
        amountTxt.setText("");
        paymentMethodCombo.setValue(null);
        referenceTxt.setText("");
        datePicker.setValue(null);
    }
}
