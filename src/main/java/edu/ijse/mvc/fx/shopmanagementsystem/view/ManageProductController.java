package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ManageProductController {

    private final ProductController productController = new ProductController();

    @FXML
    private CheckBox activeChk;

    @FXML
    private TextField barcodeTxt;

    @FXML
    private TextField categoryIDTxt;

    @FXML
    private TableColumn<ProductDTO, Boolean> colActive;

    @FXML
    private TableColumn<ProductDTO, Integer> colBarcode;

    @FXML
    private TableColumn<ProductDTO, String> colCategoryID;

    @FXML
    private TableColumn<ProductDTO, String> colCustomerID;

    @FXML
    private TableColumn<ProductDTO, String> colName;

    @FXML
    private TableColumn<ProductDTO, String> colProductID;

    @FXML
    private TableColumn<ProductDTO, String> colSKU;

    @FXML
    private TableColumn<ProductDTO, Double> colTaxRate;

    @FXML
    private TableColumn<ProductDTO, Integer> colUnit;

    @FXML
    private TableColumn<ProductDTO, Double> colUnitPrice;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField productIDTxt;

    @FXML
    private ImageView productImageView;

    @FXML
    private TableView<ProductDTO> productTable;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField skuTxt;

    @FXML
    private TextField taxRateTxt;

    @FXML
    private TextField unitPriceTxt;

    @FXML
    private TextField unitTxt;

    @FXML
    private Button updateBtn;

    @FXML
    void initialize() {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colSKU.setCellValueFactory(new PropertyValueFactory<>("sku"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colTaxRate.setCellValueFactory(new PropertyValueFactory<>("taxRate"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        try {
            productTable.getItems().clear();
            productTable.getItems().addAll(productController.getAllProducts());
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try{
            String res = productController.deleteProduct(productIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        productIDTxt.clear();
        nameTxt.clear();
        barcodeTxt.clear();
        skuTxt.clear();
        unitPriceTxt.clear();
        unitTxt.clear();
        taxRateTxt.clear();
        categoryIDTxt.clear();
        customerIDTxt.clear();
        activeChk.setSelected(false);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ProductDTO productDTO = new ProductDTO(
                    productIDTxt.getText(),
                    nameTxt.getText(),
                    Integer.parseInt(barcodeTxt.getText()),
                    skuTxt.getText(),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Integer.parseInt(unitTxt.getText()),
                    Double.parseDouble(taxRateTxt.getText()),
                    categoryIDTxt.getText(),
                    customerIDTxt.getText(),
                    activeChk.isSelected()
            );
            String res = productController.saveProduct(productDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            ProductDTO productDTO = new ProductDTO(
                    productIDTxt.getText(),
                    nameTxt.getText(),
                    Integer.parseInt(barcodeTxt.getText()),
                    skuTxt.getText(),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Integer.parseInt(unitTxt.getText()),
                    Double.parseDouble(taxRateTxt.getText()),
                    categoryIDTxt.getText(),
                    customerIDTxt.getText(),
                    activeChk.isSelected()
            );
            String res = productController.updateProduct(productDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
