package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.InventoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.InventoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageInventoryController {

    private final InventoryController inventoryController = new InventoryController();

    @FXML
    private TableColumn<InventoryDTO, String> colLastStockUpdate;

    @FXML
    private TableColumn<InventoryDTO, String> colProductId;

    @FXML
    private TableColumn<InventoryDTO, Integer> colQYT;

    @FXML
    private TableColumn<InventoryDTO, Integer> colReorderLevel;

    @FXML
    private TableColumn<InventoryDTO, Integer> colReorderQYT;

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
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String res = inventoryController.deleteInventory(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,res);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        idTxt.clear();
        qytTxt.clear();
        reOrderLevelTxt.clear();
        reOrderQYTLabel.setText("0");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    idTxt.getText(),
                    Integer.parseInt(qytTxt.getText()),
                    Integer.parseInt(reOrderLevelTxt.getText()),
                    Integer.parseInt(reOrderQYTLabel.getText()),
                    null
            );
            String res = inventoryController.saveInventory(inventoryDTO);
            new Alert(Alert.AlertType.INFORMATION,res);
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
                    Integer.parseInt(reOrderQYTLabel.getText()),
                    null
            );
            String res = inventoryController.updateInventory(inventoryDTO);
            new Alert(Alert.AlertType.INFORMATION,res);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

}
