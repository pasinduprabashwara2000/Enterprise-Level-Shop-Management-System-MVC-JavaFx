package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.RoleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.UserDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.RoleController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class ManageRoleController {

    private final RoleController roleController = new RoleController();
    private final UserController userController = new UserController();

    @FXML
    private ComboBox<String> ComboUserId;

    @FXML
    private TableColumn<RoleDTO, String> colRoleID;

    @FXML
    private TableColumn<RoleDTO, String> colRoleName;

    @FXML
    private TableColumn<RoleDTO, String> colUserID;

    @FXML
    private TableView<RoleDTO> detailsTable;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField roleIDTxt;

    @FXML
    public void initialize() {
        colRoleID.setCellValueFactory(new PropertyValueFactory<>("roleID"));
        colRoleName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        loadTable();
        loadComboUserId();

        detailsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                loadSelectedRow();
            }
        });
    }

    private void loadSelectedRow() {
        RoleDTO selected = detailsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            roleIDTxt.setText(selected.getRoleID());
            nameTxt.setText(selected.getName());
            ComboUserId.setValue(selected.getUserID());
        }
    }

    public void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(roleController.getAllRoles());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void loadComboUserId() {
        try {
            ArrayList<UserDTO> users = userController.getAllUsers();
            ObservableList<String> userIds = FXCollections.observableArrayList();
            for (UserDTO user : users) {
                userIds.add(user.getUserID());
            }
            ComboUserId.setItems(userIds);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = roleController.deleteRole(roleIDTxt.getText());
            loadTable();
            navigateReset(event);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        roleIDTxt.setText("");
        nameTxt.setText("");
        ComboUserId.setValue(null);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            RoleDTO roleDTO = new RoleDTO(
                    null,
                    nameTxt.getText(),
                    ComboUserId.getValue()
            );

            String rsp = roleController.saveRole(roleDTO);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            RoleDTO roleDTO = new RoleDTO(
                    roleIDTxt.getText(),
                    nameTxt.getText(),
                    ComboUserId.getValue()
            );

            String rsp = roleController.updateRole(roleDTO);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
