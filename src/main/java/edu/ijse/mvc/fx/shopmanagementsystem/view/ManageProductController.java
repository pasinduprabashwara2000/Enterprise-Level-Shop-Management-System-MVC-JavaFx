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
    private TableColumn<ProductDTO, String> colBarcode;

    @FXML
    private TableColumn<ProductDTO, String> colCategoryID;

    @FXML
    private TableColumn<ProductDTO, String> colName;

    @FXML
    private TableColumn<ProductDTO, String> colProductID;

    @FXML
    private TableColumn<ProductDTO, String> colSKU;

    @FXML
    private TableColumn<ProductDTO, Double> colTaxRate;

    @FXML
    private TableColumn<ProductDTO, String> colUnit;

    @FXML
    private TableColumn<ProductDTO, Double> colUnitPrice;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField productIDTxt;

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
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = productController.deleteProduct(productIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        productIDTxt.clear();
        skuTxt.clear();
        barcodeTxt.clear();
        nameTxt.clear();
        unitTxt.clear();
        unitPriceTxt.clear();
        taxRateTxt.clear();
        activeChk.setSelected(false);
        categoryIDTxt.clear();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ProductDTO productDTO = new ProductDTO(
                    productIDTxt.getText(),
                    skuTxt.getText(),
                    Integer.parseInt(barcodeTxt.getText()),
                    nameTxt.getText(),
                    unitTxt.getText(),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Double.parseDouble(taxRateTxt.getText()),
                    activeChk.isSelected(),
                    categoryIDTxt.getText()
            );
            String rsp = productController.saveProduct(productDTO);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            ProductDTO productDTO = new ProductDTO(
                    productIDTxt.getText(),
                    skuTxt.getText(),
                    Integer.parseInt(barcodeTxt.getText()),
                    nameTxt.getText(),
                    unitTxt.getText(),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Double.parseDouble(taxRateTxt.getText()),
                    activeChk.isSelected(),
                    categoryIDTxt.getText()
            );
            String rsp = productController.updateProduct(productDTO);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
