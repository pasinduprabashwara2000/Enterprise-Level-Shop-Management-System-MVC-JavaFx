package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplierDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PurchaceOrderController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SupplierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import java.util.ArrayList;

public class ManagePurchaseOrderController {

    private final PurchaceOrderController purchaceOrderController = new PurchaceOrderController();
    private final SupplierController supplierController = new SupplierController();

    @FXML
    private TableColumn<PurchaceOrderDTO, Date> colCreatedAt;

    @FXML
    private TableColumn<PurchaceOrderDTO, String> colCreatedBy;

    @FXML
    private TableColumn<PurchaceOrderDTO, Date> colExpectedDate;

    @FXML
    private TableColumn<PurchaceOrderDTO, String> colPOID;

    @FXML
    private TableColumn<PurchaceOrderDTO, String> colStatus;

    @FXML
    private TableColumn<PurchaceOrderDTO, String> colSupplierID;

    @FXML
    private TableColumn<PurchaceOrderDTO, Double> colTotalCost;

    @FXML
    private DatePicker createdAtPicker;

    @FXML
    private TextField createdByTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private DatePicker expectedDatePicker;

    @FXML
    private TextField poIdTxt;

    @FXML
    private TableView<PurchaceOrderDTO> poTable;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private ComboBox<String> supplierIdCombo;

    @FXML
    private TextField totalCostTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize(){
        colPOID.setCellValueFactory(new PropertyValueFactory<>("poId"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colExpectedDate.setCellValueFactory(new PropertyValueFactory<>("expectedDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        loadTable();
        loadSupplierId();

        poTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });

    }

    private void loadSelectedRow(){

        PurchaceOrderDTO purchaceOrderDTO = poTable.getSelectionModel().getSelectedItem();

        if(poTable != null){
            poIdTxt.setText(purchaceOrderDTO.getPoId());
            supplierIdCombo.setValue(purchaceOrderDTO.getSupplierId());
            createdAtPicker.setValue(purchaceOrderDTO.getCreatedAt());
            createdByTxt.setText(purchaceOrderDTO.getCreatedBy());
            statusCombo.setValue(purchaceOrderDTO.getStatus());
            expectedDatePicker.setValue(purchaceOrderDTO.getExpectedDate());
            totalCostTxt.setText(String.valueOf(purchaceOrderDTO.getTotalCost()));
        }

    }

    private void loadTable(){
        try {
            poTable.getItems().clear();
            poTable.getItems().addAll(purchaceOrderController.getAllPurchaceOrders());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    private void loadSupplierId(){
        try {
            ArrayList <SupplierDTO> supplierDTOS = supplierController.getAllSuppliers();
            ObservableList <String> list = FXCollections.observableArrayList();

            for (SupplierDTO supplierDTO : supplierDTOS){
                list.add(supplierDTO.getSupplierID());
            }

            supplierIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = purchaceOrderController.deletePurchaceOrder(poIdTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        poIdTxt.setText("");
        supplierIdCombo.setValue(null);
        createdAtPicker.setValue(null);
        createdByTxt.setText("");
        statusCombo.setValue(null);
        expectedDatePicker.setValue(null);
        totalCostTxt.setText("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PurchaceOrderDTO purchaceOrderDTO = new PurchaceOrderDTO(
                null,
                supplierIdCombo.getValue(),
                createdAtPicker.getValue(),
                createdByTxt.getText(),
                statusCombo.getValue(),
                expectedDatePicker.getValue(),
                Double.parseDouble(totalCostTxt.getText())
            );
            String rsp = purchaceOrderController.savePurchaceOrder(purchaceOrderDTO);
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
            PurchaceOrderDTO purchaceOrderDTO = new PurchaceOrderDTO(
                    poIdTxt.getText(),
                    supplierIdCombo.getValue(),
                    createdAtPicker.getValue(),
                    createdByTxt.getText(),
                    statusCombo.getValue(),
                    expectedDatePicker.getValue(),
                    Double.parseDouble(totalCostTxt.getText())
            );
            String rsp = purchaceOrderController.updatePurchaceOrder(purchaceOrderDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
