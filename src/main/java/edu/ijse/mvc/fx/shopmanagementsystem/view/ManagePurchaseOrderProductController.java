package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PurchaceOrderProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePurchaseOrderProductController {

    private final PurchaceOrderProductController purchaceOrderProductController = new PurchaceOrderProductController();

    @FXML
    private TableColumn<PurchaceOrderProductDTO, Double> colLineTotal;

    @FXML
    private TableColumn<PurchaceOrderProductDTO, String> colPoId;

    @FXML
    private TableColumn<PurchaceOrderProductDTO, String> colPoItemId;

    @FXML
    private TableColumn<PurchaceOrderProductDTO, Integer> colQuantityOrdered;

    @FXML
    private TableColumn<PurchaceOrderProductDTO, Integer> colQuantityReceived;

    @FXML
    private TableColumn<PurchaceOrderProductDTO, Double> colUnitCost;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<PurchaceOrderProductDTO> detailsTable;

    @FXML
    private TextField lineTotalTxt;

    @FXML
    private TextField poIdTxt;

    @FXML
    private TextField poItemIdTxt;

    @FXML
    private TextField quantityOrderedTxt;

    @FXML
    private TextField quantityReceivedTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField unitCostTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize(){
        colPoItemId.setCellValueFactory(new PropertyValueFactory<>("poItemId"));
        colPoId.setCellValueFactory(new PropertyValueFactory<>("poId"));
        colQuantityOrdered.setCellValueFactory(new PropertyValueFactory<>("quantityOrdered"));
        colQuantityReceived.setCellValueFactory(new PropertyValueFactory<>("quantityReceived"));
        colUnitCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colLineTotal.setCellValueFactory(new PropertyValueFactory<>("lineTotal"));

        loadTable();
    }

    private void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(purchaceOrderProductController.getAllPurchaceOrderProducts());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = purchaceOrderProductController.deletePurchaceOrderProduct(poItemIdTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        poItemIdTxt.setText("");
        poIdTxt.setText("");
        quantityOrderedTxt.setText("");
        quantityReceivedTxt.setText("");
        unitCostTxt.setText("");
        lineTotalTxt.setText("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PurchaceOrderProductDTO purchaceOrderProductDTO = new PurchaceOrderProductDTO(
                poItemIdTxt.getText(),
                poIdTxt.getText(),
                Integer.parseInt(quantityOrderedTxt.getText()),
                Integer.parseInt(quantityReceivedTxt.getText()),
                Double.parseDouble(unitCostTxt.getText()),
                Double.parseDouble(lineTotalTxt.getText())
            );
            String rsp = purchaceOrderProductController.savePurchaceOrderProduct(purchaceOrderProductDTO);
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
            PurchaceOrderProductDTO purchaceOrderProductDTO = new PurchaceOrderProductDTO(
                    poItemIdTxt.getText(),
                    poIdTxt.getText(),
                    Integer.parseInt(quantityOrderedTxt.getText()),
                    Integer.parseInt(quantityReceivedTxt.getText()),
                    Double.parseDouble(unitCostTxt.getText()),
                    Double.parseDouble(lineTotalTxt.getText())
            );
            String rsp = purchaceOrderProductController.updatePurchaceOrderProduct(purchaceOrderProductDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
