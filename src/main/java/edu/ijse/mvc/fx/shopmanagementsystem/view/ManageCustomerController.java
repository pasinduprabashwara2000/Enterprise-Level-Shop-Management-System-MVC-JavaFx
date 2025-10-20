package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<CustomerDTO> detailsTabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTxt;

    @FXML
    private Label idLabel;

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

    public void initialize() throws Exception {
       colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
       colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colCusNum.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
       colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("email"));
       colLoyaltyCode.setCellValueFactory(new PropertyValueFactory<>("loyaltyCode"));

       loadTable();
    }

    public void loadTable(){
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(customerController.getAllCustomers());
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
        idTxt.clear();
        nameTxt.clear();
        contactNoTxt.clear();
        emailTxt.clear();
        loyaltyCodeTxt.clear();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try{
          CustomerDTO customerDTO = new CustomerDTO(
                  idTxt.getText(),
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
