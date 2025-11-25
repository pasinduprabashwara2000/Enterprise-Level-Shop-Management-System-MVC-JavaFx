package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ReturnController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;

public class ManageReturnController {

    final private ReturnController returnController = new ReturnController();

    @FXML
    private TableColumn<ReturnDTO, String> colProccesedBy;

    @FXML
    private TableColumn<ReturnDTO, String> colReason;

    @FXML
    private TableColumn<ReturnDTO, Date> colReturnDate;

    @FXML
    private TableColumn<ReturnDTO, String> colReturnID;

    @FXML
    private TableColumn<ReturnDTO, String> colSaleID;

    @FXML
    private TableColumn<ReturnDTO, String> colStatus;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField processedByTxt;

    @FXML
    private TextField reasonTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField returnIDTxt;

    @FXML
    private TableView<ReturnDTO> returnTable;

    @FXML
    private TextField saleIDTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> statusCmb;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize(){
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnID"));
        colSaleID.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        colProccesedBy.setCellValueFactory(new PropertyValueFactory<>("processedBy"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDateTime"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();

    }

    private void loadTable(){
        try{
          returnTable.getItems().clear();
          returnTable.getItems().addAll(returnController.getAllReturns());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = returnController.deleteReturn(returnIDTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        colReturnID.setText("");
        colSaleID.setText("");
        colProccesedBy.setText("");
        colReturnDate.setText("");
        colReason.setText("");
        colStatus.setText("");
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ReturnDTO returnDTO = new ReturnDTO(
                    returnIDTxt.getText(),
                    saleIDTxt.getText(),
                    processedByTxt.getText(),
                    returnDatePicker.getValue(),
                    reasonTxt.getText(),
                    statusCmb.getValue()
            );
            String rsp = returnController.saveReturn(returnDTO);
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
            ReturnDTO returnDTO = new ReturnDTO(
                    returnIDTxt.getText(),
                    saleIDTxt.getText(),
                    processedByTxt.getText(),
                    returnDatePicker.getValue(),
                    reasonTxt.getText(),
                    statusCmb.getValue()
            );
            String rsp = returnController.updateReturn(returnDTO);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
