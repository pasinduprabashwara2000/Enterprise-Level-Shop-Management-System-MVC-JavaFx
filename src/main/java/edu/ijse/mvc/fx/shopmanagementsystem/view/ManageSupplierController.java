package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplierDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SupplierController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSupplierController {

    final private SupplierController supplierController = new SupplierController();

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTxt;

    @FXML
    private TableColumn<SupplierDTO, String> colAddress;

    @FXML
    private TableColumn<SupplierDTO, String> colContactPerson;

    @FXML
    private TableColumn<SupplierDTO, String> colEmail;

    @FXML
    private TableColumn<SupplierDTO, String> colId;

    @FXML
    private TableColumn<SupplierDTO, String> colName;

    @FXML
    private TableColumn<SupplierDTO, Integer> colPhone;

    @FXML
    private Label contactPersonLabel;

    @FXML
    private TextField contactPersonTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<SupplierDTO> detailsTable;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTxt;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField supplierIDTxt;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        loadTable();

        detailsTable.setOnMouseClicked( event -> {

            if(event.getClickCount() == 1) {
                loadSelectedRow();
            }
        });

    }

    private void loadSelectedRow(){

        SupplierDTO selectedItem = detailsTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            supplierIDTxt.setText(selectedItem.getSupplierID());
            nameTxt.setText(selectedItem.getName());
            contactPersonTxt.setText(selectedItem.getContactPerson());
            phoneTxt.setText(String.valueOf(selectedItem.getPhone()));
            emailTxt.setText(selectedItem.getEmail());
            addressTxt.setText(selectedItem.getAddress());
        }

    }

    public void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(supplierController.getAllSuppliers());
        }catch(Exception e){
            new Alert(AlertType.ERROR,e.getMessage());
        }
    }        

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = supplierController.deleteSupplier(supplierIDTxt.getText());
            new Alert(AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        supplierIDTxt.setText("");
        nameTxt.setText("");
        contactPersonTxt.setText("");
        phoneTxt.setText("");
        emailTxt.setText("");
        addressTxt.setText("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try{
            SupplierDTO supplier = new SupplierDTO(
                    null,
                    nameTxt.getText(),
                    contactPersonTxt.getText(),
                    Integer.parseInt(phoneTxt.getText()),
                    emailTxt.getText(),
                    addressTxt.getText()
            );
            String rsp = supplierController.saveSupplier(supplier);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            SupplierDTO supplierDTO = new SupplierDTO(
                    supplierIDTxt.getText(),
                    nameTxt.getText(),
                    contactPersonTxt.getText(),
                    Integer.parseInt(phoneTxt.getText()),
                    emailTxt.getText(),
                    addressTxt.getText()
            );
            String rsp = supplierController.updateSupplier(supplierDTO);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
