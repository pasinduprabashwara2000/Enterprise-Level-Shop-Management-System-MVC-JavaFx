package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ExpenseDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ExpenseModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageExpenseController {

    private final ExpenseModel expenseModel = new ExpenseModel();

    @FXML
    private TextField amountTxt;

    @FXML
    private TableColumn<ExpenseDTO, Double> colAmount;

    @FXML
    private TableColumn<ExpenseDTO, String> colCategory;

    @FXML
    private TableColumn<ExpenseDTO, Object> colDate;

    @FXML
    private TableColumn<ExpenseDTO, String> colId;

    @FXML
    private TableColumn<ExpenseDTO, String> colPaymentMethod;

    @FXML
    private TableColumn<ExpenseDTO, String> colTitle;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<ExpenseDTO> expenseTable;

    @FXML
    private TextField idTxt;

    @FXML
    private ComboBox<String> methodPicker;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField titleTxt;

    @FXML
    private ComboBox<String> typePicker;

    @FXML
    private Button updateBtn;

    @FXML
    void initialize() {

        // table column mapping
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryType"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        typePicker.setItems(FXCollections.observableArrayList(
                "Food", "Transport", "Utility", "Salary", "Other"
        ));

        methodPicker.setItems(FXCollections.observableArrayList(
                "Cash", "Card", "Bank", "Online"
        ));

        expenseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, exp) -> {
            if (exp != null) {
                idTxt.setText(exp.getId());
                titleTxt.setText(exp.getTitle());
                typePicker.setValue(exp.getCategoryType());
                amountTxt.setText(String.valueOf(exp.getAmount()));
                methodPicker.setValue(exp.getPaymentMethod());
                datePicker.setValue(exp.getDate());
            }
        });

        loadTable();
    }

    private void loadTable() {
        try {
            expenseTable.setItems(
                    FXCollections.observableArrayList(
                            expenseModel.getAllExpenses()
                    )
            );
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,
                    "Failed to load expenses: " + e.getMessage()).show();
        }
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            ExpenseDTO dto = new ExpenseDTO(
                    null,
                    titleTxt.getText(),
                    typePicker.getValue(),
                    Double.parseDouble(amountTxt.getText()),
                    methodPicker.getValue(),
                    datePicker.getValue()
            );

            String rsp = expenseModel.saveExpense(dto);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            ExpenseDTO dto = new ExpenseDTO(
                    idTxt.getText(),
                    titleTxt.getText(),
                    typePicker.getValue(),
                    Double.parseDouble(amountTxt.getText()),
                    methodPicker.getValue(),
                    datePicker.getValue()
            );

            String rsp = expenseModel.updateExpense(dto);
            new Alert(Alert.AlertType.INFORMATION, rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String rsp = expenseModel.deleteExpense(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
       titleTxt.setText("");
       typePicker.setValue(null);
       amountTxt.setText("");
       methodPicker.setValue(null);
       datePicker.setValue(null);
    }
}
