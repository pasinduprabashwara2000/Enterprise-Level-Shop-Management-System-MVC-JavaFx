package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageCustomerController {

    private final CustomerController customerController = new CustomerController();

    @FXML
    private TableColumn<CustomerDTO, String> colCusId;

    @FXML
    private TableColumn<CustomerDTO, String> colCusName;

    @FXML
    private TableColumn<CustomerDTO, Integer> colCusNum;

    @FXML
    private TableColumn<CustomerDTO, String> colEmailAddress;

    @FXML
    private TableColumn<CustomerDTO, String> colLoyaltyCode;

    @FXML
    private Label contactNoLabel;

    @FXML
    private TextField contactNoTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<CustomerDTO> detailsTable;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private Label loyaltyCodeLabel;

    @FXML
    private TextField loyaltyCodeTxt;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    public void initialize() {
       colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
       colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colCusNum.setCellValueFactory(new PropertyValueFactory<>("phone"));
       colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("email"));
       colLoyaltyCode.setCellValueFactory(new PropertyValueFactory<>("loyaltyCode"));

       loadTable();
       detailsTable.setOnMouseClicked(event -> {
           if(event.getClickCount() == 1){
               loadRowData();
           }
       });

    }

    private void loadRowData(){
        CustomerDTO selectedCustomer = detailsTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null){
            idTxt.setText(selectedCustomer.getCustomerId());
            nameTxt.setText(selectedCustomer.getName());
            contactNoTxt.setText(String.valueOf(selectedCustomer.getPhone()));
            emailTxt.setText(selectedCustomer.getEmail());
            loyaltyCodeTxt.setText(selectedCustomer.getLoyaltyCode());
        }
    }

    public void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(customerController.getAllCustomers());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try{
            String res = customerController.deleteCustomer(idTxt.getText());
            loadTable();
            navigateReset(event);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        nameTxt.clear();
        contactNoTxt.clear();
        emailTxt.clear();
        loyaltyCodeTxt.clear();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try{
          CustomerDTO customerDTO = new CustomerDTO(
                  null,
                  nameTxt.getText(),
                  Integer.parseInt(contactNoTxt.getText()),
                  emailTxt.getText(),
                  loyaltyCodeTxt.getText()
          );
          String res = customerController.saveCustomer(customerDTO);
          new Alert(Alert.AlertType.INFORMATION, res).show();
          loadTable();
          navigateReset(event);
        } catch (Exception e){
          new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try{
            CustomerDTO customerDTO = new CustomerDTO(
                    idTxt.getText(),
                    nameTxt.getText(),
                    Integer.parseInt(contactNoTxt.getText()),
                    emailTxt.getText(),
                    loyaltyCodeTxt.getText()
            );
            String res = customerController.updateCustomer(customerDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
