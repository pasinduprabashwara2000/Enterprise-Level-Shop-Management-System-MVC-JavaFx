package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageReturnProductController {

    private final ReturnProductController returnProductController = new ReturnProductController();

    @FXML
    private ComboBox<String> actionCmb;

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
    private TextField productIDTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private TextField refundAmountTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField returnIDTxt;

    @FXML
    private TextField returnItemIDTxt;

    @FXML
    private TableView<ReturnProductDTO> returnProductTable;

    @FXML
    private TextField saleItemIDTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize(){
        colReturnItemID.setCellValueFactory(new PropertyValueFactory<>("returnItemId"));
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSaleItemID.setCellValueFactory(new PropertyValueFactory<>("saleItemId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colRefundAmount.setCellValueFactory(new PropertyValueFactory<>("refundAmount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        loadTable();
    }

    private void loadTable(){
        try {
            returnProductTable.getItems().clear();
            returnProductTable.getItems().addAll(returnProductController.getAllReturnProducts());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = returnProductController.deleteReturnProduct(returnItemIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        returnItemIDTxt.setText("");
        returnIDTxt.setText("");
        productIDTxt.setText("");
        saleItemIDTxt.setText("");
        quantityTxt.setText("");
        refundAmountTxt.setText("");
        actionCmb.setValue("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ReturnProductDTO returnProductDTO = new ReturnProductDTO(
                    returnItemIDTxt.getText(),
                    returnIDTxt.getText(),
                    productIDTxt.getText(),
                    saleItemIDTxt.getText(),
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
                    returnItemIDTxt.getText(),
                    returnIDTxt.getText(),
                    productIDTxt.getText(),
                    saleItemIDTxt.getText(),
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

