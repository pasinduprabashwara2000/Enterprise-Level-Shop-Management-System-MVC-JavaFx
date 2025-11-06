package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.InventoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.InventoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ManageInventoryController {

    private final InventoryController inventoryController = new InventoryController();

    @FXML
    private TableColumn<InventoryDTO, LocalDate> colLastStockUpdate;

    @FXML
    private TableColumn<InventoryDTO, String> colProductId;

    @FXML
    private TableColumn<InventoryDTO, Integer> colQYT;

    @FXML
    private TableColumn<InventoryDTO, Integer> colReorderLevel;

    @FXML
    private TableColumn<InventoryDTO, Integer> colReorderQYT;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<InventoryDTO> detailsTabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private Label loyaltyCodeLabel;

    @FXML
    private Label qytLabel;

    @FXML
    private TextField qytTxt;

    @FXML
    private Label reOrderLevelLabel;

    @FXML
    private TextField reOrderLevelTxt;

    @FXML
    private Label reOrderQYTLabel;

    @FXML
    private TextField reOrderQytTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize(){
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colQYT.setCellValueFactory(new PropertyValueFactory<>("QYT"));
        colReorderLevel.setCellValueFactory(new PropertyValueFactory<>("reOrderLevel"));
        colReorderQYT.setCellValueFactory(new PropertyValueFactory<>("reOrderQYT"));
        colLastStockUpdate.setCellValueFactory(new PropertyValueFactory<>("lastStockUpdate"));
        
        loadTable();
    }

    public void loadTable(){
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(inventoryController.getAllInventories());    
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String res = inventoryController.deleteInventory(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,res);
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        idTxt.clear();
        qytTxt.clear();
        reOrderLevelTxt.clear();
        reOrderQytTxt.clear();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    idTxt.getText(),
                    Integer.parseInt(qytTxt.getText()),
                    Integer.parseInt(reOrderLevelTxt.getText()),
                    Integer.parseInt(reOrderQytTxt.getText()),
                    datePicker.getValue()
            );
            String res = inventoryController.saveInventory(inventoryDTO);
            new Alert(Alert.AlertType.INFORMATION,res);
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    idTxt.getText(),
                    Integer.parseInt(qytTxt.getText()),
                    Integer.parseInt(reOrderLevelTxt.getText()),
                    Integer.parseInt(reOrderQytTxt.getText()),
                    datePicker.getValue()
            );
            String res = inventoryController.updateInventory(inventoryDTO);
            new Alert(Alert.AlertType.INFORMATION,res);
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

}
