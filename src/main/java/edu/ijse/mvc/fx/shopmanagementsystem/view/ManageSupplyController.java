package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplyDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SupplyController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSupplyController {

    final private SupplyController supplyController = new SupplyController();

    @FXML
    private TableColumn<SupplyDTO, Double> colLastCost;

    @FXML
    private TableColumn<SupplyDTO, String> colProductId;

    @FXML
    private TableColumn<SupplyDTO, String> colSupplierId;

    @FXML
    private TableColumn<SupplyDTO, String> colSupplierProductCode;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<SupplyDTO> detailsTable;

    @FXML
    private Label lastCostLabel;

    @FXML
    private TextField lastCostTxt;

    @FXML
    private Label productIDLabel;

    @FXML
    private TextField productIDTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label supplierCodeLabel;

    @FXML
    private TextField supplierCodeTxt;

    @FXML
    private Label supplierIDLabel;

    @FXML
    private TextField supplierTxt;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize(){
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colLastCost.setCellValueFactory(new PropertyValueFactory<>("lastCost"));
        colSupplierProductCode.setCellValueFactory(new PropertyValueFactory<>("supplierProductCode"));
        
        loadTable();

    }

    public void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(supplyController.getAllSupplies());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = supplyController.deleteSupply(productIDTxt.getText(), supplierTxt.getText());
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
    }

    }

    @FXML
    void navigateReset(ActionEvent event) {
        productIDTxt.setText("");
        supplierTxt.setText("");
        lastCostTxt.setText("");
        supplierCodeTxt.setText("");
        
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try{
            SupplyDTO supplyDTO = new SupplyDTO(
                    productIDTxt.getText(),
                    supplierTxt.getText(),
                    Double.parseDouble(lastCostTxt.getText()),
                    supplierCodeTxt.getText()
            );
            String rsp = supplyController.saveSupply(supplyDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);     
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            SupplyDTO supplyDTO = new SupplyDTO(
                    productIDTxt.getText(),
                    supplierTxt.getText(),
                    Double.parseDouble(lastCostTxt.getText()),
                    supplierCodeTxt.getText() 
            );
            String rsp = supplyController.updateSupply(supplyDTO);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }
    }

}
