package edu.ijse.mvc.fx.shopmanagementsystem.view;

import java.time.LocalDate;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSaleController {

    final private SaleController saleController = new SaleController();

    @FXML
    private TableColumn<SaleDTO, String> colCustomerId;

    @FXML
    private TableColumn<SaleDTO, Double> colDiscount;

    @FXML
    private TableColumn<SaleDTO, Double> colNetTotal;

    @FXML
    private TableColumn<SaleDTO, LocalDate> colSaleDate;

    @FXML
    private TableColumn<SaleDTO, String> colSaleId;

    @FXML
    private TableColumn<SaleDTO, Double> colTotalAmount;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<SaleDTO> detailsTable;

    @FXML
    private TextField discountTxt;

    @FXML
    private TextField netTotalTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField saleIdTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField totalAmountTxt;

    @FXML
    private Button updateBtn;

    @FXML
    void initialize() {
        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colSaleDate.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colNetTotal.setCellValueFactory(new PropertyValueFactory<>("netTotal"));
        
        loadTable();

    }

    private void loadTable() {
        try {
            detailsTable.getItems().setAll(saleController.getAllSales());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    
    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String res = saleController.deleteSale(saleIdTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,res).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        saleIdTxt.setText("");
        customerIdTxt.setText("");
        datePicker.setValue(null);
        totalAmountTxt.setText("");
        discountTxt.setText("");
        netTotalTxt.setText("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            SaleDTO saleDTO = new SaleDTO(
                    saleIdTxt.getText(),
                    customerIdTxt.getText(),
                    datePicker.getValue(),
                    Double.parseDouble(totalAmountTxt.getText()),
                    Double.parseDouble(discountTxt.getText()),
                    Double.parseDouble(netTotalTxt.getText())
            );
            String res = saleController.saveSale(saleDTO);
            new Alert(Alert.AlertType.INFORMATION,res).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            SaleDTO saleDTO = new SaleDTO(
                    saleIdTxt.getText(),
                    customerIdTxt.getText(),
                    datePicker.getValue(),
                    Double.parseDouble(totalAmountTxt.getText()),
                    Double.parseDouble(discountTxt.getText()),
                    Double.parseDouble(netTotalTxt.getText())
            );
            String res = saleController.updateSale(saleDTO);
            new Alert(Alert.AlertType.INFORMATION,res).show();
            navigateReset(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
