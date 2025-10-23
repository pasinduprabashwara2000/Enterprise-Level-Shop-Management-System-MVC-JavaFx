package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagePurchaseOrderProductController {

    @FXML
    private TableColumn<?, ?> colLineTotal;

    @FXML
    private TableColumn<?, ?> colPoId;

    @FXML
    private TableColumn<?, ?> colPoItemId;

    @FXML
    private TableColumn<?, ?> colQuantityOrdered;

    @FXML
    private TableColumn<?, ?> colQuantityReceived;

    @FXML
    private TableColumn<?, ?> colUnitCost;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<?> detailsTable;

    @FXML
    private TextField lineTotalTxt;

    @FXML
    private TextField poIdTxt;

    @FXML
    private TextField poItemIdTxt;

    @FXML
    private TextField quantityOrderedTxt;

    @FXML
    private TextField quantityReceivedTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField unitCostTxt;

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
