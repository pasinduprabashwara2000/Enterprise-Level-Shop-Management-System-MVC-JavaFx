package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PromotionDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PromotionController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageSaleProductController {

    final private SaleProductController saleProductController = new SaleProductController();
    final private ProductController productController = new ProductController();
    final private SaleController saleController = new SaleController();
    final private PromotionController promotionController = new PromotionController();

    @FXML
    private TableColumn<SaleProductDTO, Double> colLineDiscount;

    @FXML
    private TableColumn<SaleProductDTO, Double> colLineTax;

    @FXML
    private TableColumn<SaleProductDTO, Double> colLineTotal;

    @FXML
    private TableColumn<SaleProductDTO, String> colProductId;

    @FXML
    private TableColumn<SaleProductDTO, String> colPromotionId;

    @FXML
    private TableColumn<SaleProductDTO, Integer> colQuantity;

    @FXML
    private TableColumn<SaleProductDTO, String> colSaleId;

    @FXML
    private TableColumn<SaleProductDTO, String> colSaleProductId;

    @FXML
    private TableColumn<SaleProductDTO, Double> colUnitPrice;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField lineDiscountTxt;

    @FXML
    private TextField lineTaxTxt;

    @FXML
    private TextField lineTotalTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField saleProductIDTxt;

    @FXML
    private TableView<SaleProductDTO> saleProductTable;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField unitPriceTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private ComboBox<String> saleIdCombo;

    @FXML
    private ComboBox<String> productIdCombo;

    @FXML
    private ComboBox<String> promotionIdCombo;

    @FXML
    public void initialize(){
        colSaleProductId.setCellValueFactory(new PropertyValueFactory<>("saleProductID"));
        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colPromotionId.setCellValueFactory(new PropertyValueFactory<>("promotionID"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colLineDiscount.setCellValueFactory(new PropertyValueFactory<>("lineDiscount"));
        colLineTax.setCellValueFactory(new PropertyValueFactory<>("lineTax"));
        colLineTotal.setCellValueFactory(new PropertyValueFactory<>("lineTotal"));

        loadTable();
        loadSaleId();
        loadProductId();
        loadPromotionId();

        lineTotalTxt.setEditable(false);
        quantityTxt.textProperty().addListener((observable, oldValue, newValue) -> calculateTotal());
        unitPriceTxt.textProperty().addListener(((observable, oldValue, newValue) -> calculateTotal()));
        lineDiscountTxt.textProperty().addListener(((observable, oldValue, newValue) -> calculateTotal()));
        lineTaxTxt.textProperty().addListener((observable, oldValue, newValue) -> calculateTotal());

        saleProductTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1){
                loadSelectedProduct();
            }
        });

    }

    private void loadSelectedProduct(){
        SaleProductDTO selectedSaleProduct = saleProductTable.getSelectionModel().getSelectedItem();

        if (selectedSaleProduct != null){
            saleProductIDTxt.setText(selectedSaleProduct.getProductID());
            saleIdCombo.setValue(selectedSaleProduct.getSaleID());
            productIdCombo.setValue(selectedSaleProduct.getProductID());
            promotionIdCombo.setValue(selectedSaleProduct.getPromotionID());
            quantityTxt.setText(String.valueOf(selectedSaleProduct.getQuantity()));
            unitPriceTxt.setText(String.valueOf(selectedSaleProduct.getUnitPrice()));
            lineDiscountTxt.setText(String.valueOf(selectedSaleProduct.getLineDiscount()));
            lineTaxTxt.setText(String.valueOf(selectedSaleProduct.getLineTax()));
            lineTotalTxt.setText(String.valueOf(selectedSaleProduct.getLineTotal()));
        }
    }

    public void loadTable(){
        try {
            saleProductTable.getItems().clear();
            saleProductTable.getItems().addAll(saleProductController.getAllSaleProducts());
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
    void loadPromotionId() {
        try {
            ArrayList<PromotionDTO> promotionDTOS = promotionController.getAllPromotions();
            ObservableList <String> list = FXCollections.observableArrayList();

            for (PromotionDTO promotionDTO : promotionDTOS){
                list.add(promotionDTO.getPromoteID());
            }
            promotionIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void loadSaleId() {
        try {
            ArrayList <SaleDTO> saleDTOS = saleController.getAllSales();
            ObservableList <String> list = FXCollections.observableArrayList();

            for (SaleDTO saleDTO : saleDTOS){
                list.add(saleDTO.getSaleID());
            }

            saleIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void calculateTotal() {
        try {

            if (quantityTxt.getText().isEmpty() || unitPriceTxt.getText().isEmpty() || lineDiscountTxt.getText().isEmpty() || lineTaxTxt.getText().isEmpty()){
                lineTotalTxt.setText("");
                return;
            }

            double total = 0.0;
            int quantity = Integer.parseInt(quantityTxt.getText());
            double unitPrice = Double.parseDouble(unitPriceTxt.getText());
            double discount = Double.parseDouble(lineDiscountTxt.getText());
            double tax = Double.parseDouble(lineTaxTxt.getText());

            total = ((quantity * unitPrice)-discount)+tax;
            lineTotalTxt.setText(String.valueOf(total));

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = saleProductController.deleteSaleProduct(saleProductIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
                saleProductIDTxt.setText("");
                saleIdCombo.setValue(null);
                promotionIdCombo.setValue("");
                promotionIdCombo.setValue(null);
                quantityTxt.setText("");
                unitPriceTxt.setText("");
                lineDiscountTxt.setText("");
                lineTaxTxt.setText("");
                lineTotalTxt.setText("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            SaleProductDTO saleProductDTO = new SaleProductDTO(
                    null,
                    saleIdCombo.getValue(),
                    promotionIdCombo.getValue(),
                    productIdCombo.getValue(),
                    Integer.parseInt(quantityTxt.getText()),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Double.parseDouble(lineDiscountTxt.getText()),
                    Double.parseDouble(lineTaxTxt.getText()),
                    Double.parseDouble(lineTotalTxt.getText())
            );
            String rsp = saleProductController.saveSaleProduct(saleProductDTO);
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
            SaleProductDTO saleProductDTO = new SaleProductDTO(
                    saleProductIDTxt.getText(),
                    saleIdCombo.getValue(),
                    promotionIdCombo.getValue(),
                    promotionIdCombo.getValue(),
                    Integer.parseInt(quantityTxt.getText()),
                    Double.parseDouble(unitPriceTxt.getText()),
                    Double.parseDouble(lineDiscountTxt.getText()),
                    Double.parseDouble(lineTaxTxt.getText()),
                    Double.parseDouble(lineTotalTxt.getText())
            );
            String rsp = saleProductController.updateSaleProduct(saleProductDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}