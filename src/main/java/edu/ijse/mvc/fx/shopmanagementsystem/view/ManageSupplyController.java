package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageSupplyController {

    @FXML
    private TableColumn<?, ?> colLastCost;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierProductCode;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<?> detailsTable;

    @FXML
    private Label lastCostLabel;

    @FXML
    private TextField lastCostTxt;

    @FXML
    private Label productIDLabel;

    @FXML
    private TextField productIDTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label supplierCodeLabel;

    @FXML
    private TextField supplierCodeTxt;

    @FXML
    private Label supplierIDLabel;

    @FXML
    private TextField supplierTxt;

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
