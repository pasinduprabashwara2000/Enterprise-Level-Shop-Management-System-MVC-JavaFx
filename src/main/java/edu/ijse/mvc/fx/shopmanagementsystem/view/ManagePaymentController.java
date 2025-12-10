package edu.ijse.mvc.fx.shopmanagementsystem.view;

import java.time.LocalDate;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PaymentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePaymentController {

    private final PaymentModel paymentModel = new PaymentModel();

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
    private TableColumn<PaymentDTO, String> colSaleId;

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
    private TextField saleIdTxt;

    @FXML
    void initialize() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colReceivedAt.setCellValueFactory(new PropertyValueFactory<>("receivedAt"));

        loadTable();
    }

    private void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(paymentModel.getAllPayments());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    paymentIdTxt.getText(),
                    saleIdTxt.getText(),
                    paymentMethodCombo.getValue(),
                    Double.parseDouble(amountTxt.getText()),
                    referenceTxt.getText(),
                    datePicker.getValue()
            );
            new Alert(Alert.AlertType.INFORMATION, paymentModel.savePayment(paymentDTO)).show();
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
                    saleIdTxt.getText(),
                    paymentMethodCombo.getValue(),
                    Double.parseDouble(amountTxt.getText()),
                    referenceTxt.getText(),
                    datePicker.getValue()
            );
            new Alert(Alert.AlertType.INFORMATION, paymentModel.updatePayment(paymentDTO)).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            new Alert(Alert.AlertType.INFORMATION, paymentModel.deletePayment(paymentIdTxt.getText())).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        paymentIdTxt.setText("");
        saleIdTxt.setText("");
        amountTxt.setText("");
        paymentMethodCombo.setValue(null);
        referenceTxt.setText("");
        datePicker.setValue(null);
    }
}
