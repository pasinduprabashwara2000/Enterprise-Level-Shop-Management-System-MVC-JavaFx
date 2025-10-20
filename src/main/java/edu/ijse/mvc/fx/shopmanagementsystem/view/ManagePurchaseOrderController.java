package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagePurchaseOrderController {

    @FXML
    private TableColumn<?, ?> colCreatedAt;

    @FXML
    private TableColumn<?, ?> colCreatedBy;

    @FXML
    private TableColumn<?, ?> colExpectedDate;

    @FXML
    private TableColumn<?, ?> colPOID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TableColumn<?, ?> colTotalCost;

    @FXML
    private DatePicker createdAtPicker;

    @FXML
    private TextField createdByTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private DatePicker expectedDatePicker;

    @FXML
    private TextField poIdTxt;

    @FXML
    private TableView<?> poTable;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> statusCombo;

    @FXML
    private TextField supplierIdTxt;

    @FXML
    private TextField totalCostTxt;

    @FXML
    private Button updateBtn;

    @FXML
    void navigateDelete(ActionEvent event) {

    }

    @FXML
    void navigateReset(ActionEvent event) {

    }

    @FXML
    void navigateSave(ActionEvent event) {

    }

    @FXML
    void navigateUpdate(ActionEvent event) {

    }

}
