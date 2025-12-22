package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.UserDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Date;

public class ManageUserController {

    private final UserController userController = new UserController();

    @FXML
    private Label activeLabel;

    @FXML
    private ChoiceBox<String> activeStatusPicker;

    @FXML
    private TableColumn<UserDTO, Boolean> colActive;

    @FXML
    private TableColumn<UserDTO, Date> colCreatedAt;

    @FXML
    private TableColumn<UserDTO, String> colId;

    @FXML
    private TableColumn<UserDTO, String> colName;

    @FXML
    private TableColumn<UserDTO, String> colPassword;

    @FXML
    private Label createdAtLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<UserDTO> detailsTable;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Label userNameLabel;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField userIDTxt;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        loadTable();

        detailsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                loadSelectedRow();
            }
        });
    }

    private void loadSelectedRow() {
        UserDTO selectedUser = detailsTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userIDTxt.setText(selectedUser.getUserID());
            userNameTxt.setText(selectedUser.getUserName());
            passwordTxt.setText(selectedUser.getPassword());
            activeStatusPicker.setValue(selectedUser.getActive());
            datePicker.setValue(selectedUser.getCreatedAt());
        }
    }

    public void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(userController.getAllUsers());
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = userController.deleteUser(userIDTxt.getText());
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        userIDTxt.clear();
        userNameTxt.clear();
        passwordTxt.clear();
        activeStatusPicker.setValue(null);
        datePicker.setValue(null);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            UserDTO userDTO = new UserDTO(
                    null, 
                    userNameTxt.getText(),
                    passwordTxt.getText(),
                    activeStatusPicker.getValue(),
                    datePicker.getValue()
            );
            String rsp = userController.saveUser(userDTO);
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
            UserDTO userDTO = new UserDTO(
                    userIDTxt.getText(),
                    userNameTxt.getText(),
                    passwordTxt.getText(),
                    activeStatusPicker.getValue(),
                    datePicker.getValue()
            );
            String rsp = userController.updateUser(userDTO);
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }
}
