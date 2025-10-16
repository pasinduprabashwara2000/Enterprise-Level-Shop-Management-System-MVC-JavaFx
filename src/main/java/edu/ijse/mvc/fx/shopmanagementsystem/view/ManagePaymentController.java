package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PaymentController;
import javafx.event.ActionEvent;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePaymentController {

    private final PaymentController paymentController = new PaymentController();
    
    @FXML
    private TextField amountTxt;

    @FXML
    private TableColumn<PaymentDTO, Double> colAmount;

    @FXML
    private TableColumn<PaymentDTO, Object> colMethod;

    @FXML
    private TableColumn<PaymentDTO, String> colPaymentId;

    @FXML
    private TableColumn<PaymentDTO, Date> colReceivedAt;

    @FXML
    private TableColumn<PaymentDTO, String> colReference;

    @FXML
    private TableColumn<PaymentDTO, String> colSaleId;

    @FXML
    private Button deleteBtn;

    @FXML
    private ComboBox<PaymentDTO> methodCmb;

    @FXML
    private TextField paymentIDTxt;

    @FXML
    private TableView<PaymentDTO> paymentTable;

    @FXML
    private DatePicker receivedAtPicker;

    @FXML
    private TextField referenceTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField saleIDTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    void initialize() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colReceivedAt.setCellValueFactory(new PropertyValueFactory<>("receivedAt"));

        loadTable();
    }    

    public void loadTable(){
        try{
            paymentTable.getItems().clear();
            paymentTable.getItems().addAll(paymentController.getAllPayments());
         } catch (Exception e){
              new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
    }    

    @FXML
    void navigateDelete(ActionEvent event) {
        try{
            String res = paymentController.deletePayment(paymentIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        paymentIDTxt.clear();
        saleIDTxt.clear();
        methodCmb.setValue(null);
        amountTxt.clear();
        referenceTxt.clear();
        receivedAtPicker.setValue(null);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    paymentIDTxt.getText(),
                    saleIDTxt.getText(),
                    methodCmb.getValue().getMethod(),
                    Double.parseDouble(amountTxt.getText()),
                    referenceTxt.getText(),
                    java.sql.Date.valueOf(receivedAtPicker.getValue())
            );
            String res = paymentController.savePayment(paymentDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    paymentIDTxt.getText(),
                    saleIDTxt.getText(),
                    methodCmb.getValue().getMethod(),
                    Double.parseDouble(amountTxt.getText()),
                    referenceTxt.getText(),
                    java.sql.Date.valueOf(receivedAtPicker.getValue())
            );
            String res = paymentController.updatePayment(paymentDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
