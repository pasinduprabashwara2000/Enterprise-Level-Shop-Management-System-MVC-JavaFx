package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageReturnController {

    @FXML
    private TableColumn<?, ?> colProccesedBy;

    @FXML
    private TableColumn<?, ?> colReason;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colReturnID;

    @FXML
    private TableColumn<?, ?> colSaleID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField processedByTxt;

    @FXML
    private TextField reasonTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField returnIDTxt;

    @FXML
    private TableView<?> returnTable;

    @FXML
    private TextField saleIDTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> statusCmb;

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
