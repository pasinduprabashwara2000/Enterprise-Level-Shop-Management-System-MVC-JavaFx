package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.UserDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
    private TableColumn<ReturnDTO, Integer> colSaleID;

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
    private ComboBox<Integer> saleIdCombo;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> statusCmb;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize() throws Exception {
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnID"));
        colSaleID.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colProccesedBy.setCellValueFactory(new PropertyValueFactory<>("processedBy"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDateTime"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();
        loadSaleIdThread();
        loadUserIdThread();

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
              saleIdCombo.setValue(Integer.valueOf(returnDTO.getSaleID()));
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


    private void loadSaleIdThread() throws Exception{

        Task<ObservableList<Integer>> task = new Task(){

            ArrayList <SaleProductTM> sales = saleController.getAllSale();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(sales.stream().map(SaleProductTM::getSaleId).toList());
            }
        };
        task.setOnSucceeded(event -> saleIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR, task.getMessage()).show());
        new Thread(task).start();
    }

   private void loadUserIdThread() throws Exception{

        Task <ObservableList<String>> task = new Task() {
            ArrayList <UserDTO> users = userController.getAllUsers();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(users.stream().map(UserDTO::getUserID).toList());
            }
        };
        task.setOnSucceeded(event -> userIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
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
                    String.valueOf(saleIdCombo.getValue()),
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
                    String.valueOf(saleIdCombo.getValue()),
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
