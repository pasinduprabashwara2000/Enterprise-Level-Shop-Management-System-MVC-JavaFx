package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnController;
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

    private final ReturnController returnController = new ReturnController();
    private final CustomerController customerController = new CustomerController();

    @FXML
    private ComboBox<String> actionCmb;

    @FXML
    private TableColumn<ReturnDTO, String> colCustomerId;

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
    private ComboBox<String> customerIdCombo;

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
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
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
            returnTbl.getItems().addAll(returnController.getAllReturn());
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    void loadSelectedRow() {
        ReturnDTO selectedReturn = returnTbl.getSelectionModel().getSelectedItem();
        if (selectedReturn != null) {
            returnIDTxt.setText(selectedReturn.getReturnId());
            customerIdCombo.setValue(selectedReturn.getCustomerId());
            refundAmountTxt.setText(String.valueOf(selectedReturn.getRefundAmount()));
            reasonTxt.setText(selectedReturn.getReason());
            actionCmb.setValue(selectedReturn.getAction());
            returnDatePicker.setValue(selectedReturn.getReturnDate());
            statusCmb.setValue(selectedReturn.getStatus());
        }
    }

    private void loadCustomerIdThread() throws Exception {

        Task <ObservableList<String>> task = new Task<>() {
            ArrayList <CustomerDTO> customers =  customerController.getAllCustomers();
            @Override
            protected ObservableList<String> call() throws Exception {
                return FXCollections.observableArrayList(customers.stream().map(CustomerDTO::getCustomerId).toList());
            }
        };

        task.setOnSucceeded(event -> customerIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(AlertType.ERROR, task.getMessage()).show());
        new Thread(task).start();

    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = returnController.deleteReturn(returnIDTxt.getText());
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
        customerIdCombo.setValue(null);
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
                    customerIdCombo.getValue(),
                    Double.parseDouble(refundAmountTxt.getText()),
                    reasonTxt.getText(),
                    actionCmb.getValue(),
                    statusCmb.getValue(),
                    returnDatePicker.getValue()
            );

            String rsp = returnController.saveReturn(returnDTO);
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
                    customerIdCombo.getValue(),
                    Double.parseDouble(refundAmountTxt.getText()),
                    reasonTxt.getText(),
                    actionCmb.getValue(),
                    statusCmb.getValue(),
                    returnDatePicker.getValue()
            );

            String rsp = returnController.updateReturn(returnDTO);
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

}
