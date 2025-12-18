package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.*;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class ManageReturnProductController {

    private final ReturnProductController returnProductController = new ReturnProductController();
    private final ProductController productController = new ProductController();
    private final ReturnController returnController = new ReturnController();
    private final SaleController saleController = new SaleController();

    @FXML
    private ComboBox<String> actionCmb;

    @FXML
    private ComboBox<String> returnIdCombo;

    @FXML
    private ComboBox<String> productIdCombo;

    @FXML
    private ComboBox<Integer> saleItemIdCombo;

    @FXML
    private TableColumn<ReturnProductDTO, String> colAction;

    @FXML
    private TableColumn<ReturnProductDTO, String> colProductID;

    @FXML
    private TableColumn<ReturnProductDTO, Integer> colQuantity;

    @FXML
    private TableColumn<ReturnProductDTO, Double> colRefundAmount;

    @FXML
    private TableColumn<ReturnProductDTO, String> colReturnID;

    @FXML
    private TableColumn<ReturnProductDTO, String> colReturnItemID;

    @FXML
    private TableColumn<ReturnProductDTO, String> colSaleItemID;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField returnProductIdTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private TextField refundAmountTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TableView<ReturnProductDTO> returnProductTable;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize() throws Exception {
        colReturnItemID.setCellValueFactory(new PropertyValueFactory<>("returnItemId"));
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSaleItemID.setCellValueFactory(new PropertyValueFactory<>("saleItemId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colRefundAmount.setCellValueFactory(new PropertyValueFactory<>("refundAmount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        loadTable();
        loadProductIdThread();
        loadReturnIdThread();
        loadSaleItemId();

        returnProductTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });

    }

    private void loadTable(){
        try {
            returnProductTable.getItems().clear();
            returnProductTable.getItems().addAll(returnProductController.getAllReturnProducts());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadSelectedRow(){

        ReturnProductDTO returnProductDTO = returnProductTable.getSelectionModel().getSelectedItem();

        if(returnProductDTO != null){
            returnIdCombo.setValue(returnProductDTO.getReturnId());
            productIdCombo.setValue(returnProductDTO.getProductId());
            saleItemIdCombo.setValue(Integer.valueOf(returnProductDTO.getSaleItemId()));
            quantityTxt.setText(String.valueOf(returnProductDTO.getQuantity()));
            refundAmountTxt.setText(String.valueOf(returnProductDTO.getRefundAmount()));
            actionCmb.setValue(returnProductDTO.getAction());
        }

    }

    private void loadProductIdThread() throws Exception{
        Task <ObservableList<String>> task = new Task(){

            ArrayList <ProductDTO> products = productController.getAllProducts();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(products.stream().map(ProductDTO::getProductID));
            }
        };
        task.setOnSucceeded(event -> productIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    private void loadReturnIdThread() throws Exception {
        Task <ObservableList<String>> task = new Task() {

            ArrayList <ReturnDTO> returns = returnController.getAllReturns();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(returns.stream().map(ReturnDTO::getReturnID));
            }
        };
        task.setOnSucceeded(event -> returnIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }


    @FXML
    void loadReturnId() {
        try {
            ArrayList <ReturnDTO> returnDTOS = returnController.getAllReturns();
            ObservableList <String> list = FXCollections.observableArrayList();

            for (ReturnDTO returnDTO : returnDTOS){
                 list.add(returnDTO.getReturnID());
            }

            returnIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    @FXML
    void loadSaleItemId() {
        try {
            ArrayList <SaleProductTM> saleDTOS = saleController.getAllSale();
            ObservableList <Integer> list = FXCollections.observableArrayList();

            for (SaleProductTM saleProductTM : saleDTOS){
                list.add(saleProductTM.getSaleId());
            }

            saleItemIdCombo.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = returnProductController.deleteReturnProduct(returnProductIdTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        returnIdCombo.setValue(null);
        productIdCombo.setValue(null);
        saleItemIdCombo.setValue(null);
        quantityTxt.setText("");
        refundAmountTxt.setText("");
        actionCmb.setValue("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ReturnProductDTO returnProductDTO = new ReturnProductDTO(
                    null,
                    returnIdCombo.getValue(),
                    productIdCombo.getValue(),
                    saleItemIdCombo.getValue(),
                    Integer.parseInt(quantityTxt.getText()),
                    Double.parseDouble(refundAmountTxt.getText()),
                    actionCmb.getValue()
            );
            String rsp = returnProductController.saveReturnProduct(returnProductDTO);
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
            ReturnProductDTO returnProductDTO = new ReturnProductDTO(
                    returnProductIdTxt.getText(),
                    returnIdCombo.getValue(),
                    productIdCombo.getValue(),
                    saleItemIdCombo.getValue(),
                    Integer.parseInt(quantityTxt.getText()),
                    Double.parseDouble(refundAmountTxt.getText()),
                    actionCmb.getValue()
            );
            String rsp = returnProductController.updateReturnProduct(returnProductDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}

