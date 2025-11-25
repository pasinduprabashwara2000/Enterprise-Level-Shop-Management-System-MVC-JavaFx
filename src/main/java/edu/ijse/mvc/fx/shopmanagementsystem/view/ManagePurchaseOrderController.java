package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PurchaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;

public class ManagePurchaseOrderController {

    private final PurchaceOrderController purchaceOrderController = new PurchaceOrderController();

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
    private TextField supplierIdTxt;

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
        supplierIdTxt.setText("");
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
                poIdTxt.getText(),
                supplierIdTxt.getText(),
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
                    supplierIdTxt.getText(),
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
