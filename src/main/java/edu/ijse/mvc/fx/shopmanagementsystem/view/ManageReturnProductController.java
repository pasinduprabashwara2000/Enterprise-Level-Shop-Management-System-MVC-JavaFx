package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageReturnProductController {

    @FXML
    private ComboBox<?> actionCmb;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colProductID;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colRefundAmount;

    @FXML
    private TableColumn<?, ?> colReturnID;

    @FXML
    private TableColumn<?, ?> colReturnItemID;

    @FXML
    private TableColumn<?, ?> colSaleItemID;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private TextField refundAmountTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField returnIDTxt;

    @FXML
    private TextField returnItemIDTxt;

    @FXML
    private TableView<?> returnProductTable;

    @FXML
    private TextField saleItemIDTxt;

    @FXML
    private Button saveBtn;

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
