package edu.ijse.mvc.fx.shopmanagementsystem.view;

import java.time.LocalDate;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PaymentController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePaymentController {

    private final PaymentController paymentController = new PaymentController();
    private final SaleController saleController = new SaleController();

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
    private TableColumn<PaymentDTO, Integer> colSaleId;

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
    private ComboBox<Integer> saleIdCombo;

    @FXML
    void initialize() throws Exception {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colReceivedAt.setCellValueFactory(new PropertyValueFactory<>("receivedAt"));

        loadTable();
        loadSaleIdThread();

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
            saleIdCombo.setValue(Integer.valueOf(selectedPayment.getSaleID()));
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


    private void loadSaleIdThread() throws Exception{

        Task <ObservableList<Integer>> task = new Task() {

            ArrayList <SaleProductTM> sales = saleController.getAllSale();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(sales.stream().map(SaleProductTM::getSaleId).toList());
            }
        };
        task.setOnSucceeded(event -> saleIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    null,
                    String.valueOf(saleIdCombo.getValue()),
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
                    String.valueOf(saleIdCombo.getValue()),
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
        saleIdCombo.setValue(null);
        paymentIdTxt.setText("");
        amountTxt.setText("");
        paymentMethodCombo.setValue(null);
        referenceTxt.setText("");
        datePicker.setValue(null);
    }
}
