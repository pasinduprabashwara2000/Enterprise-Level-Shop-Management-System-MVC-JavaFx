package edu.ijse.mvc.fx.shopmanagementsystem.view;

import java.sql.Date;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PromotionDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PromotionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagePromotionController {

    private final PromotionController promotionController = new PromotionController()

    @FXML
    private ComboBox<?> activeCmb;

    @FXML
    private TextField categoryIDTxt;

    @FXML
    private TableColumn<PromotionDTO, Boolean> colActive;

    @FXML
    private TableColumn<PromotionDTO, String> colCategoryID;

    @FXML
    private TableColumn<PromotionDTO, Date> colEndDate;

    @FXML
    private TableColumn<PromotionDTO, String> colName;

    @FXML
    private TableColumn<PromotionDTO, String> colProductID;

    @FXML
    private TableColumn<PromotionDTO, String> colPromotionID;

    @FXML
    private TableColumn<PromotionDTO, Date> colStartDate;

    @FXML
    private TableColumn<PromotionDTO, Object> colType;

    @FXML
    private TableColumn<PromotionDTO, Double> colValue;

    @FXML
    private Button deleteBtn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField promoteIDTxt;

    @FXML
    private TableView<PromotionDTO> promotionTable;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<?> typeCmb;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField valueTxt;

    @FXML
    void initialize() {
        colPromotionID.setCellValueFactory(new PropertyValueFactory<>("promotionID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        loadTable();

    }
    
    @FXML
    public void loadTable(){
        try{
            promotionTable.getItems().clear();
            promotionTable.getItems().addAll(promotionController.getAllPromotions());
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }


    @FXML
    void navigateDelete(ActionEvent event) {
        try{
            String res = promotionController.deletePromotion(promoteIDTxt.getText());
            loadTable();
            navigateReset(event);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        promoteIDTxt.clear();
        nameTxt.clear();
        productIDTxt.clear();
        categoryIDTxt.clear();
        typeCmb.setValue(null);
        valueTxt.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        activeCmb.setValue(null);
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            PromotionDTO promotionDTO = new PromotionDTO(
                    promoteIDTxt.getText(),
                    nameTxt.getText(),
                    productIDTxt.getText(),
                    categoryIDTxt.getText(),
                    typeCmb.getValue(),
                    Double.parseDouble(valueTxt.getText()),
                    startDatePicker.getValue(),
                    endDatePicker.getValue(),
                    Boolean.parseBoolean(activeCmb.getValue().toString())
            );
            String res = promotionController.savePromotion(promotionDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            PromotionDTO promotionDTO = new PromotionDTO(
                    promoteIDTxt.getText(),
                    nameTxt.getText(),
                    productIDTxt.getText(),
                    categoryIDTxt.getText(),
                    typeCmb.getValue(),
                    Double.parseDouble(valueTxt.getText()),
                    startDatePicker.getValue(),
                    endDatePicker.getValue(),
                    Boolean.parseBoolean(activeCmb.getValue().toString())
            );
            String res = promotionController.updatePromotion(promotionDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}

}
