package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ManageOrderController {

    @FXML
    private Button addItemBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<?> customerIdCombo;

    @FXML
    private TextField discountTxt;

    @FXML
    private TextField netAmountTxt;

    @FXML
    private DatePicker orderDatePicker;

    @FXML
    private TableView<?> orderItemsTable;

    @FXML
    private ComboBox<?> productIdCombo;

    @FXML
    private TextField quantityTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveOrderBtn;

    @FXML
    private TextField totalAmountTxt;

    @FXML
    private TextField unitPriceTxt;

    @FXML
    void addItemToOrder(ActionEvent event) {

    }

    @FXML
    void resetForm(ActionEvent event) {

    }

    @FXML
    void saveOrder(ActionEvent event) {

    }

}
