package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.RoleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.RoleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageRoleController {

    private final RoleController roleController = new RoleController();

    @FXML
    private TableColumn<RoleDTO, String> colRoleID;

    @FXML
    private TableColumn<RoleDTO, String> colRoleName;

    @FXML
    private TableColumn<RoleDTO, String> colUserID;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<RoleDTO> detailsTable;

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
    public void initialize(){
        colRoleID.setCellValueFactory(new PropertyValueFactory<>("roleID"));
        colRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        
        loadTable();
    }

    public void loadTable(){
        try{
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(roleController.getAllRoles());
        } catch(Exception e){
            new Alert(AlertType.ERROR,e.getMessage()).show();
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
        roleIDTxt.clear();
        nameTxt.clear();
        userIDTxt.clear();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            RoleDTO roleDTO = new RoleDTO(
                    roleIDTxt.getText(),
                    nameTxt.getText(),
                    userIDTxt.getText()
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
                    userIDTxt.getText()
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
