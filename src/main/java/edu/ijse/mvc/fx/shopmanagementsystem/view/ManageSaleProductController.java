package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSaleProductController {

    final private SaleProductController saleProductController = new SaleProductController();

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
    private TextField productIDTxt;

    @FXML
    private TextField promotionIDTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField saleIDTxt;

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
    void navigateDelete(ActionEvent event) {

    }

    @FXML
    void navigateReset(ActionEvent event) {

    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            SaleProductDTO saleProductDTO = new SaleProductDTO(
                    saleProductIDTxt.getText(),
                    saleIDTxt.getText(),
                    productIDTxt.getText(),
                    productIDTxt.getText(),
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

    }

}