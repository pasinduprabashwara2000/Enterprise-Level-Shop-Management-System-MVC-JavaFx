package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CategoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CategoryController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageProductController {

    private final ProductController productController = new ProductController();
    private final CategoryController categoryController = new CategoryController();

    @FXML
    private CheckBox activeChk;

    @FXML
    private TextField barcodeTxt;

    @FXML
    private ComboBox<String> categoryCombo;

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
    private TextField nameTxt;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TableView<ProductDTO> detailsTable;

    @FXML
    private TextField skuTxt;

    @FXML
    private TextField taxRateTxt;

    @FXML
    private TextField unitPriceTxt;

    @FXML
    private TextField unitTxt;

    @FXML
    private void initialize() {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colSKU.setCellValueFactory(new PropertyValueFactory<>("SKU"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTaxRate.setCellValueFactory(new PropertyValueFactory<>("taxRate"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("categoryID"));

        loadTableThread();
        loadCategoryThread();

        detailsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1){
                loadSelectedRow();
            }
        });
    }

    private void loadSelectedRow() {
        ProductDTO p = detailsTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            productIDTxt.setText(p.getProductID());
            skuTxt.setText(p.getSKU());
            barcodeTxt.setText(String.valueOf(p.getBarCode()));
            nameTxt.setText(p.getName());
            unitTxt.setText(p.getUnit());
            unitPriceTxt.setText(String.valueOf(p.getUnitPrice()));
            taxRateTxt.setText(String.valueOf(p.getTaxRate()));
            activeChk.setSelected(p.isActive());
            categoryCombo.setValue(p.getCategoryID());
        }
    }

    private void loadTableThread() {
        Task<ObservableList<ProductDTO>> task = new Task<>() {
            @Override
            protected ObservableList<ProductDTO> call() throws Exception {
                return FXCollections.observableArrayList(productController.getAllProducts());
            }
        };
        task.setOnSucceeded(e -> detailsTable.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    private void loadCategoryThread() {
        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                ArrayList<CategoryDTO> categories = categoryController.getAllCategories();
                return FXCollections.observableArrayList(
                        categories.stream().map(CategoryDTO::getCategoryID).toList()
                );
            }
        };
        task.setOnSucceeded(e -> categoryCombo.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ProductDTO dto = new ProductDTO(
                    null,
                    skuTxt.getText(),
                    Integer.parseInt(barcodeTxt.getText()),
                    nameTxt.getText(),
                    unitTxt.getText(),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Double.parseDouble(taxRateTxt.getText()),
                    activeChk.isSelected(),
                    categoryCombo.getValue()
            );
            String rsp = productController.saveProduct(dto);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTableThread();
            navigateReset(null);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            ProductDTO dto = new ProductDTO(
                    productIDTxt.getText(),
                    skuTxt.getText(),
                    Integer.parseInt(barcodeTxt.getText()),
                    nameTxt.getText(),
                    unitTxt.getText(),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Double.parseDouble(taxRateTxt.getText()),
                    activeChk.isSelected(),
                    categoryCombo.getValue()
            );
            String rsp = productController.updateProduct(dto);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTableThread();
            navigateReset(null);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = productController.deleteProduct(productIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTableThread();
            navigateReset(null);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
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
        categoryCombo.setValue(null);
    }

}
