package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.InventoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.InventoryController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManageInventoryController {

    private final InventoryController inventoryController = new InventoryController();
    private final ProductController productController = new ProductController();

    @FXML
    private TextField inventoryID;

    @FXML
    private ComboBox<String> productIdCombo;

    @FXML
    private TextField reOrderLevelTxt;

    @FXML
    private TextField reOrderQytTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<InventoryDTO> detailsTabel;

    @FXML
    private TableColumn<InventoryDTO, String> colProductId;

    @FXML
    private TableColumn<InventoryDTO, Integer> colReorderLevel;

    @FXML
    private TableColumn<InventoryDTO, Integer> colReorderQYT;

    @FXML
    private TableColumn<InventoryDTO, LocalDate> colLastStockUpdate;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button resetBtn;

    @FXML
    public void initialize() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colReorderLevel.setCellValueFactory(new PropertyValueFactory<>("reOrderLevel"));
        colReorderQYT.setCellValueFactory(new PropertyValueFactory<>("reOrderQYT"));
        colLastStockUpdate.setCellValueFactory(new PropertyValueFactory<>("lastStockUpdate"));

        loadTable();
        loadProductIds();

        detailsTabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                loadSelectedRow();
            }
        });
    }

    private void loadTable() {
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(inventoryController.getAllInventories());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadProductIds() {
        try {
            ArrayList<ProductDTO> products = productController.getAllProducts();
            ObservableList<String> productIds = FXCollections.observableArrayList();

            for (ProductDTO product : products) {
                productIds.add(product.getProductID());
            }

            productIdCombo.setItems(productIds);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadSelectedRow() {
        InventoryDTO inventory = detailsTabel.getSelectionModel().getSelectedItem();
        if (inventory != null) {
            inventoryID.setText(inventory.getInventoryID());
            productIdCombo.setValue(inventory.getProductID());
            reOrderLevelTxt.setText(String.valueOf(inventory.getReOrderLevel()));
            reOrderQytTxt.setText(String.valueOf(inventory.getReOrderQYT()));
            datePicker.setValue(inventory.getLastStockUpdate());
        }
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            InventoryDTO dto = new InventoryDTO(
                    null,
                    productIdCombo.getValue(),
                    Integer.parseInt(reOrderLevelTxt.getText()),
                    Integer.parseInt(reOrderQytTxt.getText()),
                    datePicker.getValue()
            );

            String response = inventoryController.saveInventory(dto);
            new Alert(Alert.AlertType.INFORMATION, response).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            InventoryDTO dto = new InventoryDTO(
                    inventoryID.getText(),
                    productIdCombo.getValue(),
                    Integer.parseInt(reOrderLevelTxt.getText()),
                    Integer.parseInt(reOrderQytTxt.getText()),
                    datePicker.getValue()
            );

            String response = inventoryController.updateInventory(dto);
            new Alert(Alert.AlertType.INFORMATION, response).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String response = inventoryController.deleteInventory(inventoryID.getText());
            new Alert(Alert.AlertType.INFORMATION, response).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        inventoryID.clear();
        productIdCombo.setValue(null);
        reOrderLevelTxt.clear();
        reOrderQytTxt.clear();
        datePicker.setValue(null);
    }
}
