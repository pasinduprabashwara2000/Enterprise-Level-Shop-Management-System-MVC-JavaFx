package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.SaleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ManageSaleController {

    final private SaleController saleController = new SaleController();

    @FXML
    private TableColumn<SaleDTO, String> colCustomerID;

    @FXML
    private TableColumn<SaleDTO, LocalDate> colDate;

    @FXML
    private TableColumn<SaleDTO, Double> colDiscountTotal;

    @FXML
    private TableColumn<SaleDTO, Double> colGrandTotal;

    @FXML
    private TableColumn<SaleDTO, String> colSaleID;

    @FXML
    private TableColumn<SaleDTO, String> colStatus;

    @FXML
    private TableColumn<SaleDTO, Double> colSubTotal;

    @FXML
    private TableColumn<SaleDTO, Double> colTaxTotal;

    @FXML
    private TableColumn<SaleDTO, String> colUserID;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField discountTotalTxt;

    @FXML
    private TextField grandTotalTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField saleIDTxt;

    @FXML
    private TableView<SaleDTO> saleTable;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> statusCmb;

    @FXML
    private TextField subTotalTxt;

    @FXML
    private TextField taxTotalTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField userIDTxt;

    @FXML
    public void initialize() {
        colSaleID.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        colTaxTotal.setCellValueFactory(new PropertyValueFactory<>("taxTotal"));
        colDiscountTotal.setCellValueFactory(new PropertyValueFactory<>("discountTotal"));
        colGrandTotal.setCellValueFactory(new PropertyValueFactory<>("grandTotal"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();
    }

    public void loadTable(){
        try {
            saleTable.getItems().clear();
            saleTable.getItems().addAll(saleController.getAllModel());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = saleController.deleteModel(saleIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        saleIDTxt.setText("");
        userIDTxt.setText("");
        customerIDTxt.setText("");
        grandTotalTxt.setText("");
        statusCmb.setValue(null);
        subTotalTxt.setText("");
        taxTotalTxt.setText("");
        discountTotalTxt.setText("");
        datePicker.setValue(null);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
           SaleDTO saleDTO = new SaleDTO(
                saleIDTxt.getText(),
                userIDTxt.getText(),
                customerIDTxt.getText(),
                Double.parseDouble(subTotalTxt.getText()),
                Double.parseDouble(taxTotalTxt.getText()),
                Double.parseDouble(discountTotalTxt.getText()),
                Double.parseDouble(grandTotalTxt.getText()),
                datePicker.getValue(),
                statusCmb.getValue()
           );
           String rsp = saleController.saveModel(saleDTO);
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
            SaleDTO saleDTO = new SaleDTO(
                    saleIDTxt.getText(),
                    userIDTxt.getText(),
                    customerIDTxt.getText(),
                    Double.parseDouble(subTotalTxt.getText()),
                    Double.parseDouble(taxTotalTxt.getText()),
                    Double.parseDouble(discountTotalTxt.getText()),
                    Double.parseDouble(grandTotalTxt.getText()),
                    datePicker.getValue(),
                    statusCmb.getValue()
            );
            String rsp = saleController.updateModel(saleDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
