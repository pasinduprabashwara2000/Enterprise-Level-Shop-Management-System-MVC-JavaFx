package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplierDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplyDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SupplierController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SupplyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageSupplyController {

    final private SupplyController supplyController = new SupplyController();
    final private ProductController productController = new ProductController();
    final private SupplierController supplierController = new SupplierController();

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
    private ComboBox<String> productIdCombo;

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
    private ComboBox<String> supplierIdCombo;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize(){
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colLastCost.setCellValueFactory(new PropertyValueFactory<>("lastCost"));
        colSupplierProductCode.setCellValueFactory(new PropertyValueFactory<>("supplierProductCode"));
        
        loadTable();
        loadProductId();
        loadSupplierId();

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
    void loadProductId() {
        try {
            ArrayList <ProductDTO> productDTOS = productController.getAllProducts();
            ObservableList <String> list = FXCollections.observableArrayList();
            for (ProductDTO productDTO : productDTOS){
                list.add(productDTO.getProductID());
            }
            productIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void loadSupplierId() {
        try {
            ArrayList <SupplierDTO> supplyDTOS = supplierController.getAllSuppliers();
            ObservableList <String> list = FXCollections.observableArrayList();
            for (SupplierDTO supplierDTO : supplyDTOS){
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
            String rsp = supplyController.deleteSupply(productIdCombo.getValue(), supplierIdCombo.getValue());
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
    }

    }

    @FXML
    void navigateReset(ActionEvent event) {
        lastCostTxt.setText("");
        supplierCodeTxt.setText("");
        
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try{
            SupplyDTO supplyDTO = new SupplyDTO(
                    productIdCombo.getValue(),
                    supplierIdCombo.getValue(),
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
                    productIdCombo.getValue(),
                    supplierIdCombo.getValue(),
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
