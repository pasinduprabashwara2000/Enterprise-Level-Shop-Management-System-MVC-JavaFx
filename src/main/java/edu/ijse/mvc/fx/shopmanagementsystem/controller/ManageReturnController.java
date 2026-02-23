package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PaymentModel;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ReturnModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageReturnController {

    private final PaymentModel paymentModel = new PaymentModel();
    private final ReturnModel returnModel = new ReturnModel();

    @FXML
    private ComboBox<String> actionCmb;

    @FXML
    private TableColumn<ReturnDTO, String> colPaymentId;

    @FXML
    private TableColumn<ReturnDTO, String> colAction;

    @FXML
    private TableColumn<ReturnDTO, String> colReason;

    @FXML
    private TableColumn<ReturnDTO, Double> colRefund;

    @FXML
    private TableColumn<ReturnDTO, java.time.LocalDate> colReturnDate;

    @FXML
    private TableColumn<ReturnDTO, String> colReturnId;

    @FXML
    private TableColumn<ReturnDTO, String> colStatus;

    @FXML
    private ComboBox<String> paymentIdCombo;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField reasonTxt;

    @FXML
    private TextField refundAmountTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField returnIDTxt;

    @FXML
    private TableView<ReturnDTO> returnTbl;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> statusCmb;

    @FXML
    private Button updateBtn;

    @FXML
    void initialize() throws Exception {
        colReturnId.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colRefund.setCellValueFactory(new PropertyValueFactory<>("refundAmount"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();
        loadCustomerIdThread();

        returnTbl.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1 ){
                loadSelectedRow();
            }
        });

    }

    void loadTable(){
        try {
            returnTbl.getItems().clear();
            returnTbl.getItems().addAll(returnModel.getAllReturns());
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    void loadSelectedRow() {
        ReturnDTO selectedReturn = returnTbl.getSelectionModel().getSelectedItem();
        if (selectedReturn != null) {
            returnIDTxt.setText(selectedReturn.getReturnId());
            paymentIdCombo.setValue(selectedReturn.getPaymentId());
            refundAmountTxt.setText(String.valueOf(selectedReturn.getRefundAmount()));
            reasonTxt.setText(selectedReturn.getReason());
            actionCmb.setValue(selectedReturn.getAction());
            returnDatePicker.setValue(selectedReturn.getReturnDate());
            statusCmb.setValue(selectedReturn.getStatus());
        }
    }

    private void loadCustomerIdThread() throws Exception {

        Task <ObservableList<String>> task = new Task<>() {
            ArrayList <PaymentDTO> payments =  paymentModel.getAllPayments();
            @Override
            protected ObservableList<String> call() throws Exception {
                return FXCollections.observableArrayList(payments.stream().map(PaymentDTO::getPaymentID).toList());
            }
        };

        task.setOnSucceeded(event -> paymentIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(AlertType.ERROR, task.getMessage()).show());
        new Thread(task).start();

    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = returnModel.deleteReturn(returnIDTxt.getText());
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        returnIDTxt.clear();
        paymentIdCombo.setValue(null);
        refundAmountTxt.clear();
        reasonTxt.clear();
        actionCmb.setValue(null);
        returnDatePicker.setValue(null);
        statusCmb.setValue(null);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ReturnDTO returnDTO = new ReturnDTO(
                    null,
                    paymentIdCombo.getValue(),
                    Double.parseDouble(refundAmountTxt.getText()),
                    reasonTxt.getText(),
                    actionCmb.getValue(),
                    statusCmb.getValue(),
                    returnDatePicker.getValue()
            );

            String rsp = returnModel.saveReturn(returnDTO);
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            ReturnDTO returnDTO = new ReturnDTO(
                    returnIDTxt.getText(),
                    paymentIdCombo.getValue(),
                    Double.parseDouble(refundAmountTxt.getText()),
                    reasonTxt.getText(),
                    actionCmb.getValue(),
                    statusCmb.getValue(),
                    returnDatePicker.getValue()
            );

            String rsp = returnModel.updateReturn(returnDTO);
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

}
