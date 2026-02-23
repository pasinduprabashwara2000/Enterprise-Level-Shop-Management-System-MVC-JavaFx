package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaseOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplierDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PurchaseOrderModel;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManagePurchaseOrderController {

    private final PurchaseOrderModel purchaseOrderModel = new PurchaseOrderModel();
   private final SupplierModel supplierModel = new SupplierModel();

    @FXML
    private TableColumn<PurchaseOrderDTO, LocalDate> colCreatedAt;

    @FXML
    private TableColumn<PurchaseOrderDTO, LocalDate> colExpectedDate;

    @FXML
    private TableColumn<PurchaseOrderDTO, Integer> colPOID;

    @FXML
    private TableColumn<PurchaseOrderDTO, String> colStatus;

    @FXML
    private TableColumn<PurchaseOrderDTO, String> colSupplierID;

    @FXML
    private TableColumn<PurchaseOrderDTO, Double> colTotalCost;

    @FXML
    private DatePicker createdAtPicker;

    @FXML
    private DatePicker expectedDatePicker;

    @FXML
    private TextField poIdTxt;

    @FXML
    private TableView<PurchaseOrderDTO> poTable;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private ComboBox<String> supplierIdCombo;

    @FXML
    private TextField totalCostTxt;

    @FXML
    public void initialize() throws Exception {
        colPOID.setCellValueFactory(new PropertyValueFactory<>("poId"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colExpectedDate.setCellValueFactory(new PropertyValueFactory<>("expectedDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadSupplierIdThread();
        loadTable();

        poTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });

    }

    private void loadTable() {
        try {
            poTable.getItems().clear();
            poTable.getItems().addAll(purchaseOrderModel.getAllPurchaseOrders());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void loadSelectedRow(){
        try {
           PurchaseOrderDTO purchaseOrderDTO = poTable.getSelectionModel().getSelectedItem();

           if(purchaseOrderDTO != null){
                supplierIdCombo.setValue(purchaseOrderDTO.getSupplierId());
                createdAtPicker.setValue(purchaseOrderDTO.getCreatedAt());
                expectedDatePicker.setValue(purchaseOrderDTO.getExpectedDate());
                totalCostTxt.setText(String.valueOf(purchaseOrderDTO.getTotalCost()));
                statusCombo.setValue(purchaseOrderDTO.getStatus());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void loadSupplierIdThread() throws Exception{
        Task <ObservableList<String>> task = new Task<>() {

            ArrayList <SupplierDTO> suppliers = supplierModel.getAllSuppliers();
            @Override
            protected ObservableList<String> call() throws Exception {
                return FXCollections.observableArrayList(suppliers.stream().map(SupplierDTO::getSupplierID).toList());
            }
        };
        task.setOnSucceeded(event -> supplierIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR, task.getMessage()).show());
        new Thread(task).start();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PurchaseOrderDTO dto = new PurchaseOrderDTO(
                    null,
                    supplierIdCombo.getValue(),
                    Double.parseDouble(totalCostTxt.getText()),
                    statusCombo.getValue(),
                    createdAtPicker.getValue(),
                    expectedDatePicker.getValue()
            );
            String res = purchaseOrderModel.savePurchaseOrder(dto);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            PurchaseOrderDTO dto = new PurchaseOrderDTO(
                    poIdTxt.getText(),
                    supplierIdCombo.getValue(),
                    Double.parseDouble(totalCostTxt.getText()),
                    statusCombo.getValue(),
                    createdAtPicker.getValue(),
                    expectedDatePicker.getValue()
            );
            String res = purchaseOrderModel.updatePurchaseOrder(dto);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String res = purchaseOrderModel.deletePurchaseOrder(
                    poIdTxt.getText()
            );
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        poIdTxt.clear();
        supplierIdCombo.getSelectionModel().clearSelection();
        createdAtPicker.setValue(null);
        expectedDatePicker.setValue(null);
        totalCostTxt.clear();
        statusCombo.getSelectionModel().clearSelection();
    }
}
