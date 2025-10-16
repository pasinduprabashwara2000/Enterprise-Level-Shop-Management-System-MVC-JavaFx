package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageSaleController {

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDiscountTotal;

    @FXML
    private TableColumn<?, ?> colGrandTotal;

    @FXML
    private TableColumn<?, ?> colSaleID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colSubTotal;

    @FXML
    private TableColumn<?, ?> colTaxTotal;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField discountTotalTxt;

    @FXML
    private TextField grandTotalTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField saleIDTxt;

    @FXML
    private TableView<?> saleTable;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> statusCmb;

    @FXML
    private TextField subTotalTxt;

    @FXML
    private TextField taxTotalTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField userIDTxt;

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
