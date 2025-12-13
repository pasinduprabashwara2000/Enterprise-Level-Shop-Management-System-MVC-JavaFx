package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.UserDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import java.util.ArrayList;

public class ManageReturnController {

    final private ReturnController returnController = new ReturnController();
    final private SaleController saleController = new SaleController();
    final private UserController userController = new UserController();

    @FXML
    private TableColumn<ReturnDTO, String> colProccesedBy;

    @FXML
    private TableColumn<ReturnDTO, String> colReason;

    @FXML
    private TableColumn<ReturnDTO, Date> colReturnDate;

    @FXML
    private TableColumn<ReturnDTO, String> colReturnID;

    @FXML
    private TableColumn<ReturnDTO, String> colSaleID;

    @FXML
    private TableColumn<ReturnDTO, String> colStatus;

    @FXML
    private Button deleteBtn;

    @FXML
    private ComboBox <String> userIdCombo;

    @FXML
    private TextField reasonTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField returnIDTxt;

    @FXML
    private TableView<ReturnDTO> returnTable;

    @FXML
    private ComboBox<String> saleIdCombo;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> statusCmb;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize(){
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnID"));
        colSaleID.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colProccesedBy.setCellValueFactory(new PropertyValueFactory<>("processedBy"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDateTime"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();
        loadSaleId();
        loadUserId();

        returnTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });

    }

    private void loadSelectedRow(){

       ReturnDTO returnDTO = returnTable.getSelectionModel().getSelectedItem();

       if(returnDTO != null){
              returnIDTxt.setText(returnDTO.getReturnID());
              saleIdCombo.setValue(returnDTO.getSaleID());
              userIdCombo.setValue(returnDTO.getProcessedBy());
              returnDatePicker.setValue(returnDTO.getReturnDateTime());
              reasonTxt.setText(returnDTO.getReason());
              statusCmb.setValue(returnDTO.getStatus());
       }

    }

    private void loadTable(){
        try{
            returnTable.getItems().clear();
            returnTable.getItems().addAll(returnController.getAllReturns());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }
    }

    @FXML
    void loadSaleId() {
        try {
            ArrayList <SaleDTO> saleDTOS = saleController.getAllSales();
            ObservableList <String> list = FXCollections.observableArrayList();

            for (SaleDTO saleDTO : saleDTOS){
                list.add(saleDTO.getSaleID());
            }

            saleIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void loadUserId(){
        try {
            ArrayList <UserDTO> userDTOS = userController.getAllUsers();
            ObservableList <String> list = FXCollections.observableArrayList();

            for (UserDTO userDTO : userDTOS){
                list.add(userDTO.getUserID());
            }

            userIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = returnController.deleteReturn(returnIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        returnIDTxt.setText("");
        saleIdCombo.setValue(null);
        userIdCombo.setValue(null);
        returnDatePicker.setValue(null);
        reasonTxt.setText("");
        statusCmb.setValue("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ReturnDTO returnDTO = new ReturnDTO(
                    null,
                    saleIdCombo.getValue(),
                    userIdCombo.getValue(),
                    returnDatePicker.getValue(),
                    reasonTxt.getText(),
                    statusCmb.getValue()
            );
            String rsp = returnController.saveReturn(returnDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            ReturnDTO returnDTO = new ReturnDTO(
                    returnIDTxt.getText(),
                    saleIdCombo.getValue(),
                    userIdCombo.getValue(),
                    returnDatePicker.getValue(),
                    reasonTxt.getText(),
                    statusCmb.getValue()
            );
            String rsp = returnController.updateReturn(returnDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
