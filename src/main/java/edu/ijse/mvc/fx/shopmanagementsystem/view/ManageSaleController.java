package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.*;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageSaleController {

    private final SaleController saleController = new SaleController();
    private final CustomerController customerController = new CustomerController();
    private final ProductController productController = new ProductController();
    private final PromotionController promotionController = new PromotionController();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<SaleProductTM> saleTable;

    @FXML
    private TableColumn<SaleProductTM, Integer> colSaleId;

    @FXML
    private TableColumn<SaleProductTM, LocalDate> colSaleDate;

    @FXML
    private TableColumn<SaleProductTM, String> colCustomerId;

    @FXML
    private TableColumn<SaleProductTM, String> colPromotionId;

    @FXML
    private TableColumn<SaleProductTM, Double> colTotalAmount;

    @FXML
    private TableColumn<SaleProductTM, Double> colDiscount;

    @FXML
    private TableColumn<SaleProductTM, Double> colNetTotal;

    @FXML
    private TableColumn<SaleProductTM, String> colProductId;

    @FXML
    private TableColumn<SaleProductTM, Integer> colQty;

    @FXML
    private TextField saleIdTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> customerIdCombo;

    @FXML
    private ComboBox<String> productIdCombo;

    @FXML
    private ComboBox<String> promotionIdCombo;

    @FXML
    private TextField quantityTxt;

    @FXML
    private TextField totalAmountTxt;

    @FXML
    private TextField discountTxt;

    @FXML
    private TextField netTotalTxt;

    @FXML
    private void initialize() {

        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        colSaleDate.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colPromotionId.setCellValueFactory(new PropertyValueFactory<>("promotionId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qyt"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colNetTotal.setCellValueFactory(new PropertyValueFactory<>("netTotal"));

        datePicker.setValue(LocalDate.now());

        loadTable();
        loadCustomerIds();
        loadProductIds();
        loadPromotionIds();

        totalAmountTxt.textProperty().addListener((obs, o, n) -> calculateNetTotal());
        discountTxt.textProperty().addListener((obs, o, n) -> calculateNetTotal());

        saleTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                loadSelectedRow();
            }
        });
    }

    private void loadTable() {
        try {
            ObservableList<SaleProductTM> list =
                    FXCollections.observableArrayList(saleController.getAllSale());
            saleTable.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadSelectedRow() {
        SaleProductTM tm = saleTable.getSelectionModel().getSelectedItem();

        if (tm != null) {
            saleIdTxt.setText(String.valueOf(tm.getSaleId()));
            datePicker.setValue(tm.getSaleDate());
            customerIdCombo.setValue(tm.getCustomerId());
            productIdCombo.setValue(tm.getProductID());
            quantityTxt.setText(String.valueOf(tm.getQyt()));
            promotionIdCombo.setValue(tm.getPromotionId());
            totalAmountTxt.setText(String.valueOf(tm.getTotalAmount()));
            discountTxt.setText(String.valueOf(tm.getDiscount()));
            netTotalTxt.setText(String.valueOf(tm.getNetTotal()));
        }
    }

    private void loadCustomerIds() {
        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                ArrayList<CustomerDTO> list = customerController.getAllCustomers();
                return FXCollections.observableArrayList(
                        list.stream().map(CustomerDTO::getCustomerId).toList()
                );
            }
        };

        task.setOnSucceeded(e -> customerIdCombo.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR, task.getException().getMessage()).show());

        new Thread(task).start();
    }

    private void loadProductIds() {
        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                ArrayList<ProductDTO> list = productController.getAllProducts();
                return FXCollections.observableArrayList(
                        list.stream().map(ProductDTO::getProductID).toList()
                );
            }
        };

        task.setOnSucceeded(e -> productIdCombo.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR, task.getException().getMessage()).show());

        new Thread(task).start();
    }

    private void loadPromotionIds() {
        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                ArrayList<PromotionDTO> list = promotionController.getAllPromotions();
                return FXCollections.observableArrayList(
                        list.stream().map(PromotionDTO::getPromoteID).toList()
                );
            }
        };

        task.setOnSucceeded(e -> promotionIdCombo.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR, task.getException().getMessage()).show());

        new Thread(task).start();
    }

    private void calculateNetTotal() {
        try {
            if (totalAmountTxt.getText().isEmpty() || discountTxt.getText().isEmpty()) return;
            double total = Double.parseDouble(totalAmountTxt.getText());
            double discount = Double.parseDouble(discountTxt.getText());
            netTotalTxt.setText(String.valueOf(total - discount));
        } catch (NumberFormatException ignored) {
        }
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {

            if (customerIdCombo.getValue() == null || productIdCombo.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Select required fields").show();
                return;
            }

            List<SaleProductDTO> products = new ArrayList<>();
            products.add(new SaleProductDTO(
                    productIdCombo.getValue(),
                    Integer.parseInt(quantityTxt.getText())
            ));

            SaleDTO dto = new SaleDTO(
                    0,
                    datePicker.getValue(),
                    customerIdCombo.getValue(),
                    promotionIdCombo.getValue(),
                    Double.parseDouble(totalAmountTxt.getText()),
                    Double.parseDouble(discountTxt.getText()),
                    Double.parseDouble(netTotalTxt.getText()),
                    products
            );

            new Alert(Alert.AlertType.INFORMATION,
                    saleController.saveSale(dto)).show();

            loadTable();
            navigateReset(null);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {

            SaleDTO dto = new SaleDTO(
                    Integer.parseInt(saleIdTxt.getText()),
                    datePicker.getValue(),
                    customerIdCombo.getValue(),
                    promotionIdCombo.getValue(),
                    Double.parseDouble(totalAmountTxt.getText()),
                    Double.parseDouble(discountTxt.getText()),
                    Double.parseDouble(netTotalTxt.getText()),
                    new ArrayList<>()
            );

            new Alert(Alert.AlertType.INFORMATION,
                    saleController.updateSale(dto)).show();

            loadTable();
            navigateReset(null);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            new Alert(Alert.AlertType.INFORMATION,
                    saleController.deleteSale(Integer.parseInt(saleIdTxt.getText()))).show();
            loadTable();
            navigateReset(null);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        saleIdTxt.clear();
        quantityTxt.clear();
        totalAmountTxt.clear();
        discountTxt.clear();
        netTotalTxt.clear();
        customerIdCombo.setValue(null);
        productIdCombo.setValue(null);
        promotionIdCombo.setValue(null);
        datePicker.setValue(LocalDate.now());
        saleTable.getSelectionModel().clearSelection();
    }
}
