package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageProduct2Controller {

    private final ProductController productController = new ProductController();

    @FXML
    private TableColumn<ProductDTO, String> colProductID;

    @FXML
    private TableColumn<ProductDTO, String> colName;

    @FXML
    private TableColumn<ProductDTO, String> colCategoryID;

    @FXML
    private TableColumn<ProductDTO, String> colSKU;

    @FXML
    private TableColumn<ProductDTO, Double> colUnitPrice;

    @FXML
    private TableColumn<ProductDTO, String> colUnit;

    @FXML
    private TableColumn<ProductDTO, Double> colTaxRate;

    @FXML
    private TableColumn<ProductDTO, Boolean> colActive;

    @FXML
    private TableColumn<ProductDTO, String> colBarcode;

    @FXML
    private TableView<ProductDTO> detailsTable;

    @FXML
    private TextField idTxt;

    @FXML
    private Button searchBtn;

    @FXML
    void initialize() {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        colSKU.setCellValueFactory(new PropertyValueFactory<>("SKU"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colTaxRate.setCellValueFactory(new PropertyValueFactory<>("taxRate"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barCode"));

        loadTable();

    }

    private void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(productController.getAllProducts());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
    }

}

}
