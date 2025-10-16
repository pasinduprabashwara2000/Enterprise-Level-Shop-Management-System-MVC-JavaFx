package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageRoleController {

    @FXML
    private TableColumn<?, ?> colRoleID;

    @FXML
    private TableColumn<?, ?> colRoleName;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<?> detailsTable;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Label roleIDLabel;

    @FXML
    private TextField roleIDTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Label userIDLabel;

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
