package edu.ijse.mvc.fx.shopmanagementsystem.view;

import java.sql.Date;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PromotionDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePromotionController {

    final private PromotionController promotionController = new PromotionController();
    final private ProductController productController = new ProductController();

    @FXML
    private ComboBox<String> activeCmb;

    @FXML
    private ComboBox<String> typeCmb;

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
    private TableColumn<PromotionDTO, String> colType;

    @FXML
    private TableColumn<PromotionDTO, Double> colValue;

    @FXML
    private Button deleteBtn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField nameTxt;

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
    private Button updateBtn;

    @FXML
    private TextField valueTxt;

    @FXML
    void initialize() throws Exception {
        colPromotionID.setCellValueFactory(new PropertyValueFactory<>("promotionID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startAt"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endAt"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        typeCmb.getItems().addAll("PERCENT","AMOUNT");
        activeCmb.getItems().addAll("Active", "Inactive");

        loadTable();

        promotionTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });
    }

    private void loadSelectedRow(){
        PromotionDTO selectedPromotion = promotionTable.getSelectionModel().getSelectedItem();

        if(selectedPromotion != null){
            promoteIDTxt.setText(selectedPromotion.getPromotionID());
            nameTxt.setText(selectedPromotion.getName());
            typeCmb.setValue(selectedPromotion.getType());
            valueTxt.setText(String.valueOf(selectedPromotion.getValue()));
            startDatePicker.setValue(selectedPromotion.getStartAt().toLocalDate());
            endDatePicker.setValue(selectedPromotion.getEndAt().toLocalDate());
            activeCmb.setValue(String.valueOf(selectedPromotion.isActive()));
        }

    }

    @FXML
    public void loadTable() {
        try {
            promotionTable.getItems().clear();
            promotionTable.getItems().addAll(promotionController.getAllPromotions());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String res = promotionController.deletePromotion(promoteIDTxt.getText());
            loadTable();
            navigateReset(event);
            new Alert(Alert.AlertType.INFORMATION, res).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        promoteIDTxt.clear();
        nameTxt.clear();
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
                    null,
                    nameTxt.getText(),
                    typeCmb.getValue(),
                    Double.parseDouble(valueTxt.getText()),
                    Date.valueOf(startDatePicker.getValue()),
                    Date.valueOf(endDatePicker.getValue()),
                    Boolean.parseBoolean(activeCmb.getValue())
            );
            String res = promotionController.savePromotion(promotionDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            PromotionDTO promotionDTO = new PromotionDTO(
                    promoteIDTxt.getText(),
                    nameTxt.getText(),
                    typeCmb.getValue(),
                    Double.parseDouble(valueTxt.getText()),
                    Date.valueOf(startDatePicker.getValue()),
                    Date.valueOf(endDatePicker.getValue()),
                    Boolean.parseBoolean(activeCmb.getValue())
            );
            String res = promotionController.updatePromotion(promotionDTO);
            new Alert(Alert.AlertType.INFORMATION, res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
